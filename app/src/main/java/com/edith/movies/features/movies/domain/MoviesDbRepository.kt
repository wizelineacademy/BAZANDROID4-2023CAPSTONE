package com.edith.movies.features.movies.domain

import com.edith.movies.core.data.database.model.LastMoviesResponse
import com.edith.movies.core.data.database.entity.MovieEntity
import com.edith.movies.core.data.database.model.MovieDb
import com.edith.movies.core.data.database.model.MovieModel

interface MoviesDbRepository {

 //  suspend fun getAllNowPlayingDb(): List<MovieEntity>

    suspend fun getAllNowPlayingMovies(): List<MovieModel>
    suspend fun getlistTopRatedMovies(): List<MovieModel>
    suspend fun getLastMovie(): LastMoviesResponse?
    suspend fun getAllMovies(): List<MovieDb>


}