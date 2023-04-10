package com.andresrivas.bazpeliculasyseries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.bazpeliculasyseries.domain.usecases.GetMovieVideoUseCase
import com.andresrivas.bazpeliculasyseries.domain.usecases.GetPlayingNowUseCase
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.andresrivas.bazpeliculasyseries.utilities.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayingNowViewModel @Inject constructor(
    private val getPlayingNowUseCase: GetPlayingNowUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase
) : ViewModel() {

    private val _movies = SingleLiveEvent<ResultAPI<MoviesPagesModel>>()
    val movies: LiveData<ResultAPI<MoviesPagesModel>> get() = _movies

    private val _videoMovie = SingleLiveEvent<ResultAPI<MoviesVideoModel>>()
    val videoMovie: LiveData<ResultAPI<MoviesVideoModel>> get() = _videoMovie

    fun getPlayingNowMovies() {
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
}