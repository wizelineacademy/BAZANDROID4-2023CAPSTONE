package com.edith.movies.features.movies.domain

import com.edith.movies.core.data.MovieDb

interface MoviesDbRepository {
    suspend fun getAllMovies(): List<MovieDb>

    suspend fun getLastMovies(): List<MovieDb>
}