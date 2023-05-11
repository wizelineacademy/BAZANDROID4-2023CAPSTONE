package com.andresrivas.localdatasource.datasource

import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.common.utilities.ResultAPI

interface MoviesLocalDataSource {
    suspend fun saveMoviesPlayingNow(list: List<MovieModel>): ResultAPI<List<MovieModel>>
    suspend fun saveTopRatedMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>>
    suspend fun getNowPlayingMovies(): ResultAPI<List<MovieModel>>
    suspend fun getTopRatedMovies(): ResultAPI<List<MovieModel>>
    suspend fun saveFavoriteMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>>
    suspend fun saveFavoriteIfNoExistMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>>
    suspend fun removeFavoriteMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>>
    suspend fun getFavoriteMovies(): ResultAPI<List<MovieModel>>
    suspend fun isFavorite(movie: MovieModel): ResultAPI<Boolean>
}
