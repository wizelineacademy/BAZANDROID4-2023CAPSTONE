package mx.jramon.subias.dbmovieproyect.movies.data.dataSource.remote

import io.reactivex.rxjava3.core.Single
import mx.jramon.subias.dbmovieproyect.movies.domain.model.*
import retrofit2.http.*

interface MovieServices {

    @GET("movie/popular")
    fun getMoviesPopular(@Query("pages") page:Int): Single<MovieList>

    @GET("tv/popular")
    fun getTvSeriesPopular(@Query("pages") page:Int): Single<TvSerieList>

    @GET("movie/top_rated")
    fun getMovieTopRated(@Query("pages") page:Int): Single<MovieList>

    @GET("movie/{movie_id}")
    fun getMovieInfo(@Path("movie_id")idMovie:Int, @Query("language") language:String = "es-MX"): Single<MovieDetails>

    /*@GET("tv/{tv_id}")
    suspend fun getTvSerieInfo(@Path("tv_id")idTvSerie:Int,@Query("language")language:String): TvSerieDetails

    @GET("authentication/token/new")
    suspend fun getToken(): Tkn

    @POST("authentication/token/validate_with_login")
    suspend fun validateLogin(@Body rqTkn: RequestToken): Tkn

    @POST("authentication/session/new")
    suspend fun getSessionId(@Body rqSession: RequestSession): Session*/

}