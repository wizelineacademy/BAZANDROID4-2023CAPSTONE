package com.andresrivas.bazpeliculasyseries.data.repository.datasource

import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI

interface MoviesLocalDataSource {
    suspend fun saveMoviesPlayingNow(list: List<MovieModel>): ResultAPI<List<MovieModel>>
    suspend fun getMoviesPlayingNow(): ResultAPI<List<MovieModel>>
    suspend fun saveFavoriteMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>>
    suspend fun saveFavoriteIfNoExistMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>>
    suspend fun removeFavoriteMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>>
    suspend fun getFavoriteMovies(): ResultAPI<List<MovieModel>>
    suspend fun isFavorite(movie: MovieModel): ResultAPI<Boolean>
}