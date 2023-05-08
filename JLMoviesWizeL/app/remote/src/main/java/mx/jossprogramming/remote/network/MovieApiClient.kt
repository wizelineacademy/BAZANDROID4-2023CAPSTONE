package mx.jossprogramming.remote.network

import mx.jossprogramming.remote.models.GenresResponse
import mx.jossprogramming.remote.models.DetailMovies
import mx.jossprogramming.remote.models.MovieDbResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiClient {
    @GET("movie/now_playing")
    suspend fun getListPlayingNow(@Query("page")page:Int ): MovieDbResponse

    @GET("movie/top_rated")
    suspend fun getListTopRated(@Query("page")page:Int ): MovieDbResponse

    @GET("movie/latest")
    suspend fun getLatest(): DetailMovies

    @GET("movie/popular")
    suspend fun getLisyPopular(@Query("page")page:Int ): MovieDbResponse

    @GET("genre/movie/list")
    suspend fun getGenreList(): GenresResponse
}