package com.example.remote

import com.example.core.model.GenreModel
import com.example.core.model.MovieModel
import com.example.remote.service.MovieApiService
import com.example.network.APIServiceAutoBuilder
import com.example.remote.mapper.transformToDomain
import com.example.tools.ResultWrapper
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieApiService
): MovieRemoteDataSource {

    override suspend fun callNowPlayingMovies(): ResultWrapper<List<MovieModel>> {
        val response = APIServiceAutoBuilder.safeApiCall {
           apiService.getNowPlayingMovies()
        }
       return when(response) {
            is ResultWrapper.Success -> {
                ResultWrapper.Success(data = response.data.transformToDomain())
            }
            is ResultWrapper.Error -> {
                ResultWrapper.Error(code = response.code, message = response.message)
            }
        }
    }

    override suspend fun callLatestMovies(): ResultWrapper<MovieModel> {
        val response = APIServiceAutoBuilder.safeApiCall {
            apiService.getLatestMovies()
        }
        return when(response) {
            is ResultWrapper.Success -> {
                ResultWrapper.Success(data = response.data.transformToDomain())
            }
            is ResultWrapper.Error -> {
                ResultWrapper.Error(code = response.code, message = response.message)
            }
        }
    }

    override suspend fun callTopRatedMovies(): ResultWrapper<List<MovieModel>> {
        val response = APIServiceAutoBuilder.safeApiCall {
            apiService.getTopRatedMovies()
        }
        return when(response) {
            is ResultWrapper.Success -> {
                ResultWrapper.Success(data = response.data.transformToDomain())
            }
            is ResultWrapper.Error -> {
                ResultWrapper.Error(code = response.code, message = response.message)
            }
        }
    }

    override suspend fun callGenresMovies(): ResultWrapper<List<GenreModel>> {
        val response = APIServiceAutoBuilder.safeApiCall {
            apiService.getGenresMovies()
        }
        return when(response) {
            is ResultWrapper.Success -> {
                ResultWrapper.Success(data = response.data.transformToDomain())
            }
            is ResultWrapper.Error -> {
                ResultWrapper.Error(code = response.code, message = response.message)
            }
        }
    }
}