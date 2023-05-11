package com.andresrivas.bazpeliculasyseries.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andresrivas.bazpeliculasyseries.R
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.presentation.viewmodel.MovieDetailViewModel
import com.andresrivas.bazpeliculasyseries.uicatalog.compose.Toolbar
import com.andresrivas.bazpeliculasyseries.uicatalog.compose.model.ToolbarModel
import com.andresrivas.bazpeliculasyseries.uicatalog.theme.BazMoviesTheme
import com.andresrivas.bazpeliculasyseries.uicatalog.theme.DarkBlue
import com.andresrivas.bazpeliculasyseries.uicatalog.theme.White
import com.andresrivas.bazpeliculasyseries.utilities.Constants
import com.andresrivas.common.utilities.ResultAPI
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
                    backgroundColor = DarkBlue,
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
                is ResultAPI.OnSuccess -> isFavorite = mutableStateOf(result.data)
            }
        }
        Toolbar(
            ToolbarModel(
                "Detail movie",
                R.drawable.ic_back_arrow,
                if (isFavorite.value) R.drawable.ic_favorites_saved else R.drawable.ic_favorites,
                DarkBlue,
                White,
            ),
            onStartClick = { finish() },
            onEndClick = { movieDetailViewModel.saveFavorite(listOf(movieInfo)) },
        )
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun Content() {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(28.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(275.dp, 365.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(White)
                    .align(CenterHorizontally),
            ) {
                GlideImage(
                    model = Constants.TheMovieDBPosterBaseURL.plus(movieInfo.posterPath),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Center),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movieInfo.title,
                color = White,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Synopsis", color = White)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = movieInfo.overView, color = White)
        }
    }
}
