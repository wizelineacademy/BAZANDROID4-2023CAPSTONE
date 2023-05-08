package com.jecruzv.capstonewl.usecases

import com.jecruzv.capstonewl.util.DataState
import com.jecruzv.local.dao.MoviesDao
import com.jecruzv.local.model.Genres
import com.jecruzv.remote.data.dto.toGenres
import com.jecruzv.remote.domain.MoviesRepository
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