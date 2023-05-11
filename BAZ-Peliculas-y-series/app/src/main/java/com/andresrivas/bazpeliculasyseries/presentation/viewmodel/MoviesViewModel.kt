package com.andresrivas.bazpeliculasyseries.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.bazpeliculasyseries.domain.usecases.FavoritesUseCase
import com.andresrivas.bazpeliculasyseries.domain.usecases.GetLatestMoviesUseCase
import com.andresrivas.bazpeliculasyseries.domain.usecases.GetMovieVideoUseCase
import com.andresrivas.bazpeliculasyseries.domain.usecases.GetNowPlayingUseCase
import com.andresrivas.bazpeliculasyseries.domain.usecases.GetTopRatedUseCase
import com.andresrivas.bazpeliculasyseries.utilities.SingleLiveEvent
import com.andresrivas.common.model.MoviesVideoModel
import com.andresrivas.common.utilities.ResultAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val latestMoviesUseCase: GetLatestMoviesUseCase,
    private val getPlayingNowUseCase: GetNowPlayingUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val favoritesUseCase: FavoritesUseCase,
) : ViewModel() {

    private val disposable = CompositeDisposable()

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
        disposable.add(
            latestMoviesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe({
                    _latestMovies.postValue(it)
                }, { error ->
                    Log.e("MoviesViewModel", "Error".plus(error))
                }),
        )
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
