package com.jecruzv.capstonewl.domain.repository

import com.jecruzv.capstonewl.data.remote.dto.GenresDto
import com.jecruzv.capstonewl.data.remote.dto.MovieDetailDto
import com.jecruzv.capstonewl.data.remote.dto.MovieDto
import com.jecruzv.capstonewl.data.remote.dto.VideoDto
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): List<MovieDto>

    fun getUpcomingMovies(page: Int): Single<List<MovieDto>>

    suspend fun getTopRatedMovies(page: Int): List<MovieDto>

    suspend fun getMovie(movieId: Int): MovieDetailDto

    suspend fun getGenres(): List<GenresDto>

    suspend fun getMovieVideos(movieId: Int): List<VideoDto>

    suspend fun getSimilarMovies(movieId: Int,page:Int): List<MovieDto>

}