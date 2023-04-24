package com.edith.movies.features.movies.domain

import com.edith.movies.core.data.database.model.LastMoviesResponse
import com.edith.movies.core.data.database.entity.MovieEntity
import com.edith.movies.core.data.database.model.Movie
import com.edith.movies.core.data.database.model.MovieDb
import com.edith.movies.core.data.database.model.MovieModel

interface MoviesDbRepository {

    suspend fun getAllNowPlayingMoviesApi(): List<Movie>
    suspend fun getAllNowPlayingMoviesDb(): List<Movie>
    suspend fun insertMovies(movies: List<MovieEntity>)
    suspend fun clearMovies()
    suspend fun getlistTopRatedMovies(): List<MovieModel>
    suspend fun getLastMovie(): LastMoviesResponse?
    suspend fun getAllMovies(): List<MovieDb>


}