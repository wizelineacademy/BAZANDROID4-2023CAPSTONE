package com.edith.movies.features.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edith.movies.core.data.database.model.LastMoviesResponse
import com.edith.movies.core.data.database.entity.MovieEntity
import com.edith.movies.core.data.database.model.MovieDb
import com.edith.movies.core.data.database.model.MovieModel
import com.edith.movies.features.movies.domain.MoviesDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesDbRepository: MoviesDbRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieDb>>()
    var movies: LiveData<List<MovieDb>> = _movies

    val lastMovie: MutableLiveData<LastMoviesResponse> by lazy {
        MutableLiveData<LastMoviesResponse>()
    }
    private val _moviesModel = MutableLiveData<List<MovieModel>>()
    var moviesModel: LiveData<List<MovieModel>> = _moviesModel

    private val _topRatedMovies = MutableLiveData<List<MovieModel>>()
    var topRatedMovies : LiveData<List<MovieModel>> = _topRatedMovies
    private val _currentMovie = MutableSharedFlow<MovieEntity?>()
    val currentMovie = _currentMovie.asSharedFlow()

    fun setCurrentMovie(movieEntity: MovieEntity){
        _currentMovie.tryEmit(movieEntity)
    }


    //nowPlaying
    fun getNowPlayingMovies(){
        viewModelScope.launch {
            val result = moviesDbRepository.getAllNowPlayingMovies()
            _moviesModel.postValue(result)

        }
    }

    //topRated
    fun getTopRatedMovies(){
        viewModelScope.launch {
            val result = moviesDbRepository.getlistTopRatedMovies()
            _topRatedMovies.postValue(result)

        }
    }

        ///Ultima pelicula
    fun getLastMovie(){
        viewModelScope.launch {
            val result = moviesDbRepository.getLastMovie()
            lastMovie.postValue(result)
        }
    }

    //lista de populares
    fun getMovies() {
        viewModelScope.launch {
            val result = moviesDbRepository.getAllMovies()
            _movies.postValue(result)
        }
    }



}

