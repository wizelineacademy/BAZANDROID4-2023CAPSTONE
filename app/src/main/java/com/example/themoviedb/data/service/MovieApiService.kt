package com.example.themoviedb.data.service

import com.example.themoviedb.data.model.MovieResponse
import com.example.themoviedb.network.APIServiceAutoBuilder
import retrofit2.http.GET

const val BASE_URL_API = "https://api.themoviedb.org/3/"
interface MovieApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/latest")
    suspend fun getLatestMovies(): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieResponse

    class Builder: APIServiceAutoBuilder<MovieApiService>() {

        override fun build(): MovieApiService {
            return getInstanceRetrofit(BASE_URL_API)
        }
    }
}