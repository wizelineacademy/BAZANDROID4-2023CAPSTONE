package com.example.themoviedb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.domain.MovieModel
import com.example.themoviedb.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.themoviedb.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
) : ViewModel() {

    private val _nowPlayingState = MutableStateFlow<ResultWrapper<List<MovieModel>>?>(null)
    val nowPlayingState = _nowPlayingState.asStateFlow()

    fun callNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getNowPlayingMoviesUseCase.execute().collect {
                _nowPlayingState.emit(it)
            }
        }
    }
}