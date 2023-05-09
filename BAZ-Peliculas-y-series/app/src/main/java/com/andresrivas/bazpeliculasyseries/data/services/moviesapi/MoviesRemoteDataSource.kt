package com.andresrivas.bazpeliculasyseries.data.services.moviesapi

import com.andresrivas.bazpeliculasyseries.data.mapper.transformToDomain
import com.andresrivas.bazpeliculasyseries.data.repository.datasource.MoviesDataSource
import com.andresrivas.bazpeliculasyseries.data.services.APIService
import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val apiService: APIService,
) : MoviesDataSource {

    override suspend fun getMoviesNowPlaying(): ResultAPI<MoviesPagesModel> {
        return try {
            val response = apiService.getNowPlayingMovies()
            response.body()?.let {
                if (response.isSuccessful) {
                    ResultAPI.OnSuccess(it.transformToDomain())
                } else ResultAPI.OnFailure(Exception())
            } ?: ResultAPI.OnFailure(Exception())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun getMoviesTrending(): ResultAPI<MoviesPagesModel> {
        return try {
            val response = apiService.getTrendingMovies()
            response.body()?.let {
                if (response.isSuccessful) {
                    ResultAPI.OnSuccess(it.transformToDomain())
                } else ResultAPI.OnFailure(Exception())
            } ?: ResultAPI.OnFailure(Exception())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun getMoviesTopRated(): ResultAPI<MoviesPagesModel> {
        return try {
            val response = apiService.getTopRatedMovies()
            response.body()?.let {
                if (response.isSuccessful) {
                    ResultAPI.OnSuccess(it.transformToDomain())
                } else ResultAPI.OnFailure(Exception())
            } ?: ResultAPI.OnFailure(Exception())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun getMoviesVideo(movieId: String): ResultAPI<MoviesVideoModel> {
        return try {
            val response = apiService.getTrendingMoviesVideo(movieId)
            return response.body()?.let {
                if (response.isSuccessful) {
                    ResultAPI.OnSuccess(it.transformToDomain())
                } else ResultAPI.OnFailure(Exception())
            } ?: ResultAPI.OnFailure(Exception())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override fun getLatestMovies(): Single<ResultAPI<LatestMoviesModel>> {
        return apiService.getLatestMovies().map { response ->
            return@map response.body()?.let {
                if (response.isSuccessful) {
                    ResultAPI.OnSuccess(it.transformToDomain())
                } else ResultAPI.OnFailure(java.lang.Exception())
            } ?: ResultAPI.OnFailure(java.lang.Exception())
        }
    }
}
