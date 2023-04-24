package com.andresrivas.bazpeliculasyseries.data.services

import com.andresrivas.bazpeliculasyseries.data.services.moviesapi.response.LatestMoviesResponse
import com.andresrivas.bazpeliculasyseries.data.services.moviesapi.response.MovieResponse
import com.andresrivas.bazpeliculasyseries.data.services.moviesapi.response.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("trending/all/day")
    suspend fun getTrendingMovies(): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getTrendingMoviesVideo(
        @Path("movieId") movieId: String
    ): Response<MovieVideoResponse>

    @GET("movie/latest")
    suspend fun getLatestMovies(): Response<LatestMoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MovieResponse>
}