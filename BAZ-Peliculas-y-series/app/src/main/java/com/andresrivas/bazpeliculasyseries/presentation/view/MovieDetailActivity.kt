package com.andresrivas.bazpeliculasyseries.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.andresrivas.bazpeliculasyseries.R
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.presentation.viewmodel.MovieDetailViewModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.andresrivas.bazpeliculasyseries.uicatalog.compose.Toolbar
import com.andresrivas.bazpeliculasyseries.uicatalog.compose.model.ToolbarModel
import com.andresrivas.bazpeliculasyseries.uicatalog.theme.BazMoviesTheme
import com.andresrivas.bazpeliculasyseries.utilities.Constants
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : ComponentActivity() {

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private lateinit var movieInfo: MovieModel

    companion object {
        const val MOVIE_RESULT_MOVIE_INFO = "movieInfoExtra"
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getStringExtra(MOVIE_RESULT_MOVIE_INFO)?.let {
            movieInfo = Gson().fromJson(it, MovieModel::class.java)
            movieDetailViewModel.getMovieVideo(movieInfo.apiId)
        }
        setContent {
            BazMoviesTheme {
                Scaffold(
                    topBar = { Toolbar() },
                    content = { Content() },
                    bottomBar = {},
                )
            }
        }

        movieDetailViewModel.isFavorite(movieInfo)
    }

    @Composable
    private fun Toolbar() {
        var isFavorite = remember { mutableStateOf(false) }
        movieDetailViewModel.isFavorite.observe(this) {
            when (val result = it) {
                is ResultAPI.OnFailure -> {
                    Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
                }
                is ResultAPI.OnLoading -> {}
                is ResultAPI.OnSuccess -> {
                    isFavorite = mutableStateOf(result.data)
                    Toast.makeText(applicationContext, result.data.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        Toolbar(
            ToolbarModel(
                "",
                R.drawable.ic_back_arrow,
                if (isFavorite.value) R.drawable.ic_favorites_saved else R.drawable.ic_favorites,
                Color.Transparent
            ),
            onStartClick = { finish() },
            onEndClick = { movieDetailViewModel.saveFavorite(listOf(movieInfo)) }
        )
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun Content() {
        Column {
            GlideImage(
                model = Constants.TheMovieDBPosterBaseURL.plus(movieInfo.backdropPath),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = movieInfo.overView)
        }
    }

    /*private fun setView() {
        configToolbar()
        intent.getStringExtra(MOVIE_RESULT_MOVIE_INFO)?.let {
            movieInfo = Gson().fromJson(it, MovieModel::class.java)
            movieDetailViewModel.getMovieVideo(movieInfo.apiId)
            binding.movieTitleLabel.text = movieInfo.title
            binding.movieOverviewLabel.text = movieInfo.overView
            binding.movieOverviewLabel.setOnClickListener { movieDetailViewModel.isFavorite(movieInfo) }
        }

        movieDetailViewModel.videoMovie.observe(this) {
            when (it) {
                is ResultAPI.OnSuccess -> {
                    binding.youtubePlayerContainer.fromUrl(
                        Constants.TheMovieDBPosterBaseURL.plus(movieInfo.backdropPath)
                    )
                }
                is ResultAPI.OnFailure -> {
                    Toast.makeText(this, "Can't load movie video :'(", Toast.LENGTH_SHORT).show()
                }
                is ResultAPI.OnLoading -> {}
            }
        }

        movieDetailViewModel.isFavorite.observe(this) {
            when (val result = it) {
                is ResultAPI.OnFailure -> {
                    Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
                }
                is ResultAPI.OnLoading -> TODO()
                is ResultAPI.OnSuccess -> {
                    Toast.makeText(applicationContext, result.data.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }*/

    /*private fun configToolbar() {
        binding.favoritesToolbar.backArrowIv.setOnClickListener { finish() }
        binding.favoritesToolbar.favoriteIv.setOnClickListener {
            movieDetailViewModel.saveFavorite(arrayListOf(movieInfo))
        }
    }*/
}