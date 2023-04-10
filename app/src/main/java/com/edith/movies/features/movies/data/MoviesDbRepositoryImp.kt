package com.edith.movies.features.movies.data

import com.edith.movies.core.data.MovieDb
import com.edith.movies.core.service.api.ApiService
import com.edith.movies.features.movies.domain.MoviesDbRepository

class MoviesDbRepositoryImp(private val apiService: ApiService) : MoviesDbRepository {

    override suspend fun getAllMovies(): List<MovieDb> {
        val response = apiService.listPopularMovies("7175cc36abf3c4b497b768214f16ef0b")
        return if (response.isSuccessful) {
            response.body()!!.results
        } else {
            emptyList()
        }
    }

    override suspend fun getLastMovies(): List<MovieDb>{
        val response = apiService.listNowPlayingMovies("7175cc36abf3c4b497b768214f16ef0b")
        return if (response.isSuccessful) {
            response.body()!!.results
        } else {
            emptyList()
        }

    }

}