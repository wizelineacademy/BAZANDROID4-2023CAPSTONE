package com.edith.movies.core.service.api

import com.edith.movies.core.data.database.model.LastMoviesResponse
import com.edith.movies.core.data.database.model.MoviesDbResponse
import com.edith.movies.core.data.database.model.NowPlayingResponse
import com.edith.movies.core.data.database.model.TopRatedMoviesResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun listPopularMovies(@Query("api_key") apiKey: String): Response<MoviesDbResponse>

    @GET("movie/latest")
    suspend fun lastMovie(@Query("api_key") apiKey: String): Response<LastMoviesResponse>


    @GET("movie/now_playing")
    suspend fun listNowPlayingMovies(@Query("api_key") apiKey: String): Response<NowPlayingResponse>

    @GET("movie/top_rated")
    suspend fun listTopRatedMovies(@Query("api_key") apiKey: String): Response<TopRatedMoviesResponse>


}