package com.jecruzv.capstonewl.domain.usecases

import com.jecruzv.capstonewl.data.model.MoviesDao
import com.jecruzv.capstonewl.data.remote.dto.toTopRatedMovie
import com.jecruzv.capstonewl.domain.model.TopRatedMovie
import com.jecruzv.capstonewl.domain.repository.MoviesRepository
import com.jecruzv.capstonewl.util.DataState
import com.jecruzv.capstonewl.util.ExploreType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get explore content from repository call.
 */
class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository,
    private val moviesDao: MoviesDao
) {
    operator fun invoke(page: Int, exploreType: ExploreType) : Flow<DataState<List<TopRatedMovie>>> = flow {
        try {
            emit(DataState.Loading())
            var resources = repository.getTopRatedMovies(page).map { it.toTopRatedMovie() }
            resources = resources.filterNot { it.poster_path.isNullOrBlank() }
            moviesDao.addTopRatedMovies(resources)
            emit(DataState.Success(resources))
        } catch (e: HttpException) {
            emit(DataState.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(DataState.Error("Revisa tu internet"))
        }
    }
}