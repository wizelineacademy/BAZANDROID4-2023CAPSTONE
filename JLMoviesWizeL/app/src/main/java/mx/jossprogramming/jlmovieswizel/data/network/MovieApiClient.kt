package mx.jossprogramming.jlmovieswizel.data.network

import mx.jossprogramming.jlmovieswizel.data.models.DetailMovies
import mx.jossprogramming.jlmovieswizel.data.models.GenresResponse
import mx.jossprogramming.jlmovieswizel.data.models.MovieDbResponse
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