package com.edith.movies.core.service.api

import com.edith.movies.core.data.MoviesDbResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun listPopularMovies(@Query("api_key") apiKey: String): Response<MoviesDbResponse>

    @GET("movie/now_playing")
    suspend fun listNowPlayingMovies(@Query("api_key") apiKey: String): Response<MoviesDbResponse>
}