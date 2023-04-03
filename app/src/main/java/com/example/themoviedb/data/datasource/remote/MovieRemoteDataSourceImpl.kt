package com.example.themoviedb.data.datasource.remote

import com.example.themoviedb.data.model.MovieResponse
import com.example.themoviedb.data.service.MovieApiService
import com.example.themoviedb.network.APIServiceAutoBuilder
import com.example.themoviedb.util.ResultWrapper
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieApiService
): MovieRemoteDataSource {

    override suspend fun callNowPlayingMovies(): ResultWrapper<MovieResponse> {
        return APIServiceAutoBuilder.safeApiCall(Dispatchers.IO) {
            apiService.getNowPlayingMovies()
        }
    }

    override suspend fun callLatestMovies(): ResultWrapper<MovieResponse> {
        return APIServiceAutoBuilder.safeApiCall(Dispatchers.IO) {
            apiService.getLatestMovies()
        }
    }

    override suspend fun callTopRatedMovies(): ResultWrapper<MovieResponse> {
        return APIServiceAutoBuilder.safeApiCall(Dispatchers.IO) {
            apiService.getTopRatedMovies()
        }
    }
}