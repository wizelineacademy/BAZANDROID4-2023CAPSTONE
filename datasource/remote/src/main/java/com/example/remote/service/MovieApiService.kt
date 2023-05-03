package com.example.remote.service

import com.example.remote.response.GenresResponse
import com.example.remote.response.LatestResponse
import com.example.remote.response.MovieResponse
import com.example.network.APIServiceAutoBuilder
import retrofit2.http.GET

const val BASE_URL_API = "https://api.themoviedb.org/3/"
interface MovieApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/latest")
    suspend fun getLatestMovies(): LatestResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET("genre/movie/list")
    suspend fun getGenresMovies(): GenresResponse

    class Builder: APIServiceAutoBuilder<MovieApiService>() {

        override fun build(): MovieApiService {
            return getInstanceRetrofit(BASE_URL_API)
        }
    }
}