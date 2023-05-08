package com.jecruzv.capstonewl.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jecruzv.capstonewl.usecases.GetMovieUseCase
import com.jecruzv.capstonewl.usecases.GetSimilarUseCase
import com.jecruzv.capstonewl.usecases.GetVideosUseCase
import com.jecruzv.capstonewl.ui.fragment.SimilarMoviesState
import com.jecruzv.capstonewl.util.DataState
import com.jecruzv.local.Constants
import com.jecruzv.local.dao.MoviesDao
import com.jecruzv.local.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getVideosUseCase: GetVideosUseCase,
    private val getSimilarUseCase: GetSimilarUseCase,
    private val moviesDao: MoviesDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieState = mutableStateOf(MovieState())
    val movieState: State<MovieState> = _movieState

    private val _videosState = mutableStateOf(MovieVideosState())
    val videosState: State<MovieVideosState> = _videosState

    private val _similarState = mutableStateOf(SimilarMoviesState())
    val similarState: State<SimilarMoviesState> = _similarState
    val similarMoviesPage = mutableStateOf(1)
    private var similarMoviesPosition = 0

    var rating = mutableStateOf(0f)

    var movieId = 0
    var rate = 0
    var movie: MovieDetail? = null

    init {
        savedStateHandle.get<Int>(Constants.PARAM_MOVIE_ID)?.let {
            movieId = it
        }
        savedStateHandle.get<Double>(Constants.PARAM_MOVIE_RATING)?.let {
            getMovie(movieId)
            getVideos(movieId)
            getSimilarMovies(movieId,it)
        }
    }

    private fun getMovie(movieId: Int) {
        getMovieUseCase(movieId).onEach { result ->
            when (result) {
                is DataState.Loading -> {
                    _movieState.value = MovieState(isLoading = true)
                }
                is DataState.Success -> {
                    _movieState.value = MovieState(movie = result.data)
                }
                is DataState.Error -> {
                    _movieState.value = MovieState(movie = moviesDao.getMovie(movieId))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getVideos(movieId: Int) {
        getVideosUseCase(movieId).onEach { result ->
            when (result) {
                is DataState.Loading -> {
                    _videosState.value = MovieVideosState(isLoading = true)
                }
                is DataState.Success -> {
                    _videosState.value = MovieVideosState(videos = result.data ?: emptyList())
                }
                is DataState.Error -> {
                    _videosState.value = MovieVideosState(error = result.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSimilarMovies(movieId: Int,rate:Double) {
        getSimilarUseCase(movieId,1).onEach { result ->
            when (result) {
                is DataState.Loading -> {
                    _similarState.value = SimilarMoviesState(isLoading = true)
                }
                is DataState.Success -> {
                    //result.data?.forEach { it.vote_average = rate }
                    _similarState.value = SimilarMoviesState(movies = result.data ?: emptyList())
                }
                is DataState.Error -> {
                    _similarState.value = SimilarMoviesState(error = result.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMoreSimilarMovies() {
        if ((similarMoviesPosition + 1) >= (similarMoviesPage.value * 20)) {
            similarMoviesPage.value = similarMoviesPage.value + 1
            if (similarMoviesPage.value > 1) {
                getSimilarUseCase(movieId, similarMoviesPage.value).onEach { result ->
                    when (result) {
                        is DataState.Success -> {
                            result.data?.forEach { it.vote_average = rate.toDouble() }
                            _similarState.value = SimilarMoviesState(
                                movies = _similarState.value.movies.plus(result.data.orEmpty())
                            )
                        }
                        else -> {
                            _similarState.value = SimilarMoviesState(
                                movies = _similarState.value.movies.plus(emptyList())
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    fun onChangePosition(position: Int) {
        similarMoviesPosition = position
    }
}