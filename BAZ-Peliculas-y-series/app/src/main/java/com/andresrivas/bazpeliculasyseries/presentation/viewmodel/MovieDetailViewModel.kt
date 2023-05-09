package com.andresrivas.bazpeliculasyseries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.domain.usecases.FavoritesUseCase
import com.andresrivas.bazpeliculasyseries.domain.usecases.GetMovieVideoUseCase
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.andresrivas.bazpeliculasyseries.utilities.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieVideoUseCase: GetMovieVideoUseCase,
    private val favoritesUseCase: FavoritesUseCase,
) : ViewModel() {

    private val _videoMovie = SingleLiveEvent<ResultAPI<MoviesVideoModel>>()
    val videoMovie: LiveData<ResultAPI<MoviesVideoModel>> get() = _videoMovie

    private val _favoriteMovie = SingleLiveEvent<ResultAPI<List<MovieModel>>>()
    val favoriteMovie: LiveData<ResultAPI<List<MovieModel>>> get() = _favoriteMovie

    private val _isFavorite = SingleLiveEvent<ResultAPI<Boolean>>()
    val isFavorite: LiveData<ResultAPI<Boolean>> = _isFavorite

    fun getMovieVideo(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieVideoUseCase.execute(GetMovieVideoUseCase.Params(movieId)).collect {
                _videoMovie.postValue(it)
            }
        }
    }

    fun saveFavorite(movieModel: List<MovieModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesUseCase.saveFavoritesIfNoExist(movieModel).collect {
                _favoriteMovie.postValue(it)
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
