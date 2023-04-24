package com.andresrivas.bazpeliculasyseries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.domain.usecases.*
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.andresrivas.bazpeliculasyseries.utilities.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val latestMoviesUseCase: GetLatestMoviesUseCase,
    private val getPlayingNowUseCase: GetNowPlayingUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val favoritesUseCase: FavoritesUseCase
) : ViewModel() {

    private val _latestMovies = SingleLiveEvent<ResultAPI<LatestMoviesModel>>()
    val latestMovies: LiveData<ResultAPI<LatestMoviesModel>> get() = _latestMovies

    private val _movies = SingleLiveEvent<ResultAPI<MoviesPagesModel>>()
    val movies: LiveData<ResultAPI<MoviesPagesModel>> get() = _movies

    private val _videoMovie = SingleLiveEvent<ResultAPI<MoviesVideoModel>>()
    val videoMovie: LiveData<ResultAPI<MoviesVideoModel>> get() = _videoMovie

    private val _favorites = SingleLiveEvent<ResultAPI<List<MovieModel>>>()
    val favorites: LiveData<ResultAPI<List<MovieModel>>> = _favorites

    private val _isFavorite = SingleLiveEvent<ResultAPI<Boolean>>()
    val isFavorite: LiveData<ResultAPI<Boolean>> = _isFavorite

    fun getLatestMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            latestMoviesUseCase.execute().collect { _latestMovies.postValue(it) }
        }
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getPlayingNowUseCase.execute().collect { _movies.postValue(it) }
        }
    }

    fun getMovieVideo(idMovie: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieVideoUseCase.execute(GetMovieVideoUseCase.Params(idMovie)).collect {
                _videoMovie.postValue(it)
            }
        }
    }

    fun getTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getTopRatedUseCase.execute().collect { _movies.postValue(it) }
        }
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesUseCase.getFavoriteMovies().collect {
                _favorites.postValue(it)
            }
        }
    }

    fun isFavorite(movieModel: MovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesUseCase.isFavorite(movieModel).collect {
                _isFavorite.postValue(it)
            }
        }
    }
}