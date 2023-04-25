package com.jecruzv.capstonewl.data

import com.jecruzv.capstonewl.data.model.MoviesResponse
import com.jecruzv.capstonewl.util.Annotations
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.*

@Annotations("Entregable 1")
interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Deferred<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String
    ): Deferred<MoviesResponse>

    @Annotations("Entregable 2")
    @GET("movie/upcoming")
    fun getUpcomingMoviesFlow(
        @Query("api_key") apiKey: String
    ): MoviesResponse

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String
    ): Single<MoviesResponse>

}