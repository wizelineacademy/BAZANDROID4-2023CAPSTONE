package com.example.themoviedb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.domain.MovieModel
import com.example.themoviedb.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.themoviedb.domain.usecase.ObserveNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    observeNowPlayingMoviesUseCase: ObserveNowPlayingUseCase
) : ViewModel() {

    val nowPlayingFlow: Flow<List<MovieModel>> = observeNowPlayingMoviesUseCase.execute()
    val latestFlow: Flow<List<MovieModel>> = observeNowPlayingMoviesUseCase.execute()
    val topRatedFlow: Flow<List<MovieModel>> = observeNowPlayingMoviesUseCase.execute()

    fun callNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getNowPlayingMoviesUseCase.execute()
        }
    }
}