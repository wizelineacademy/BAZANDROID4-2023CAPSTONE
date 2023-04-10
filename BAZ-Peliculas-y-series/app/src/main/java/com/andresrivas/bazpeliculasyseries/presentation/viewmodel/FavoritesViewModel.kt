package com.andresrivas.bazpeliculasyseries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.domain.usecases.FavoritesUseCase
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.andresrivas.bazpeliculasyseries.utilities.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesUseCase: FavoritesUseCase
) : ViewModel() {

    private val _favorites = SingleLiveEvent<ResultAPI<List<MovieModel>>>()
    val favorites: LiveData<ResultAPI<List<MovieModel>>> = _favorites

    private val _isFavorite = SingleLiveEvent<ResultAPI<Boolean>>()
    val isFavorite: LiveData<ResultAPI<Boolean>> = _isFavorite

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