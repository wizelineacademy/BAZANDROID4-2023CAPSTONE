package com.jecruzv.capstonewl.domain.usecases

import com.jecruzv.capstonewl.data.model.MoviesDao
import com.jecruzv.capstonewl.data.remote.dto.toMovie
import com.jecruzv.capstonewl.domain.model.MovieDetail
import com.jecruzv.capstonewl.domain.repository.MoviesRepository
import com.jecruzv.capstonewl.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get movie from repository call.
 */
class GetMovieUseCase @Inject constructor(
    private val repository: MoviesRepository,
    private val moviesDao: MoviesDao
) {
    operator fun invoke(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        try {
            emit(DataState.Loading())
            val movie = repository.getMovie(movieId).toMovie()
            delay(250L)
            moviesDao.addMovie(movie)
            emit(DataState.Success(movie))
        } catch (e: HttpException) {
            emit(DataState.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(DataState.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}