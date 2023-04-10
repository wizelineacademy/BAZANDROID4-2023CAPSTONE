package com.andresrivas.bazpeliculasyseries.data.services

import com.andresrivas.bazpeliculasyseries.data.services.moviesapi.response.MovieResponse
import com.andresrivas.bazpeliculasyseries.data.services.moviesapi.response.MovieVideoResponse
import com.andresrivas.bazpeliculasyseries.utilities.Constants.Companion.TheMovieDBAPIKey
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("trending/all/day")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String = TheMovieDBAPIKey): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getPlayingNowMovies(@Query("api_key") apiKey: String = TheMovieDBAPIKey): Response<MovieResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getTrendingMoviesVideo(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = TheMovieDBAPIKey
    ): Response<MovieVideoResponse>
}