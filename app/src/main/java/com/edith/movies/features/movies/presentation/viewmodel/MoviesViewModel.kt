package com.edith.movies.features.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edith.movies.core.data.MovieDb
import com.edith.movies.features.movies.domain.GetMoviesUseCase
import com.edith.movies.features.movies.domain.MoviesDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesDbRepository: MoviesDbRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieDb>>()
    var movies: LiveData<List<MovieDb>> = _movies


    fun getMovies() {
        viewModelScope.launch {
            val result = moviesDbRepository.getAllMovies()
            _movies.postValue(result)
        }
    }
}

