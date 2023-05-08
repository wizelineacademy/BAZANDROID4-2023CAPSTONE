package com.jecruzv.capstonewl.domain.usecases

import com.jecruzv.capstonewl.data.model.MoviesDao
import com.jecruzv.capstonewl.data.remote.dto.toGenres
import com.jecruzv.capstonewl.data.remote.dto.toMovie
import com.jecruzv.capstonewl.data.remote.dto.toPopularMovie
import com.jecruzv.capstonewl.domain.model.Genres
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
class GetGenresUseCase @Inject constructor(
    private val repository: MoviesRepository,
    private val moviesDao: MoviesDao
) {
    operator fun invoke(): Flow<DataState<List<Genres>>> = flow {
        try {
            emit(DataState.Loading())
            val genres = repository.getGenres().map { it.toGenres() }
            delay(250L)
            moviesDao.insertGenres(genres)
            emit(DataState.Success(genres))
        } catch (e: HttpException) {
            emit(DataState.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(DataState.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}