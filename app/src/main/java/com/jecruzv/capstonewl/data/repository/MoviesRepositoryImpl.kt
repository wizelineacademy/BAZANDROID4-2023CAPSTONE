package com.jecruzv.capstonewl.data.repository

import com.jecruzv.capstonewl.data.remote.MovieApiService
import com.jecruzv.capstonewl.data.remote.dto.GenresDto
import com.jecruzv.capstonewl.data.remote.dto.MovieDetailDto
import com.jecruzv.capstonewl.data.remote.dto.MovieDto
import com.jecruzv.capstonewl.data.remote.dto.VideoDto
import com.jecruzv.capstonewl.domain.repository.MoviesRepository
import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.capstonewl.util.Constants.API_KEY
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@Annotations("Entregable 1")
class MoviesRepositoryImpl @Inject constructor(
    private val service: MovieApiService
) : MoviesRepository {
    override suspend fun getPopularMovies(page: Int): List<MovieDto> {
        return service.getPopularMovies(API_KEY).await().results!!
    }

    override fun getUpcomingMovies(page: Int): Single<List<MovieDto>> {
        return service.getUpcomingMovies(API_KEY)
            .map { response -> response.results!! }
    }

    override suspend fun getTopRatedMovies(page: Int): List<MovieDto> {
        return service.getTopRatedMovies(API_KEY).await().results!!
    }

    override suspend fun getMovie(movieId: Int): MovieDetailDto {
        return service.getMovie(movieId,API_KEY,"es-MX")
    }

    override suspend fun getMovieVideos(movieId: Int): List<VideoDto>  {
        return service.getMovieVideos(movieId,API_KEY,"es-MX").results
    }

    override suspend fun getSimilarMovies(movieId: Int,page:Int): List<MovieDto> {
        return service.getSimilarMovies(movieId,API_KEY,page,"es-MX").results!!
    }

    override suspend fun getGenres(): List<GenresDto> {
        return service.getGenres(API_KEY,"es-MX").genres
    }

}