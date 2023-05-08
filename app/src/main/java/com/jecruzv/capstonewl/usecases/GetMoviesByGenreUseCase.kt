package com.jecruzv.capstonewl.usecases

import com.jecruzv.local.model.MovieDetail
import com.jecruzv.capstonewl.util.DataState
import com.jecruzv.local.dao.MoviesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get explore content from repository call.
 */
class GetMoviesByGenreUseCase @Inject constructor(
    private val moviesDao: MoviesDao
) {
    operator fun invoke(genre: String) : Flow<DataState<List<MovieDetail>>> = flow {
        try {
            emit(DataState.Loading())
            var resources = moviesDao.getMoviesByGenre(genre).first()
            emit(DataState.Success(resources))
        } catch (e: HttpException) {
            emit(DataState.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(DataState.Error("Revisa tu internet"))
        }
    }
}