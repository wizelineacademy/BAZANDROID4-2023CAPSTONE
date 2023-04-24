package com.example.themoviedb.data.datasource.remote

import com.example.themoviedb.data.mapper.transformToDomain
import com.example.themoviedb.data.service.MovieApiService
import com.example.themoviedb.domain.GenreModel
import com.example.themoviedb.domain.MovieModel
import com.example.themoviedb.network.APIServiceAutoBuilder
import com.example.themoviedb.util.ResultWrapper
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