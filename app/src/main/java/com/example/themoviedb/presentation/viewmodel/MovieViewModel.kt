package com.example.themoviedb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.domain.GenreModel
import com.example.themoviedb.domain.MovieModel
import com.example.themoviedb.domain.usecase.*
import com.example.themoviedb.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getLatestMovieUseCase: GetLatestMovieUseCase,
    private val getGenresMoviesUseCase: GetGenresMoviesUseCase,
    private val getGenresMovieUseCase: GetGenresMovieUseCase
) : ViewModel() {

    private val _nowPlayingState = MutableStateFlow<ResultWrapper<List<MovieModel>>?>(null)
    val nowPlayingState = _nowPlayingState.asStateFlow()

    private val _topRatedState = MutableStateFlow<ResultWrapper<List<MovieModel>>?>(null)
    val topRatedState = _topRatedState.asStateFlow()

    private val _latestState = MutableStateFlow<ResultWrapper<MovieModel>?>(null)
    val latestState = _latestState.asStateFlow()

    private val _genresMovieState = MutableStateFlow<List<GenreModel>?>(null)
    val genresMovieState = _genresMovieState.asStateFlow()

    fun callNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getNowPlayingMoviesUseCase.execute().collect {
                _nowPlayingState.emit(it)
            }
        }
    }

    fun callTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getTopRatedMoviesUseCase.execute().collect {
                _topRatedState.emit(it)
            }
        }
    }

    fun callLatestMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            getLatestMovieUseCase.execute().collect {
                _latestState.emit(it)
            }
        }
    }

    fun callGenresMovies() {
        viewModelScope.launch {
            getGenresMoviesUseCase.execute().collect()
        }
    }

    fun getGenresMovie(ids: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val response = getGenresMovieUseCase.execute(ids)) {
                is ResultWrapper.Error -> {}
                is ResultWrapper.Success -> {
                    _genresMovieState.emit(response.data)
                }
            }
        }
    }
}