package com.andresrivas.bazpeliculasyseries.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.andresrivas.bazpeliculasyseries.MoviesApplication
import com.andresrivas.bazpeliculasyseries.databinding.ActivityMovieDetailBinding
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.presentation.viewmodel.MovieDetailViewModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.andresrivas.bazpeliculasyseries.utilities.Constants
import com.andresrivas.bazpeliculasyseries.utilities.fromUrl
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private lateinit var movieInfo: MovieModel

    companion object {
        const val MOVIE_RESULT_MOVIE_INFO = "movieInfoExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            setView()
        }
    }

    private fun setView() {
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
    }

    private fun configToolbar() {
        binding.favoritesToolbar.backArrowIv.setOnClickListener { finish() }
        binding.favoritesToolbar.favoriteIv.setOnClickListener {
            movieDetailViewModel.saveFavorite(arrayListOf(movieInfo))
        }
    }
}