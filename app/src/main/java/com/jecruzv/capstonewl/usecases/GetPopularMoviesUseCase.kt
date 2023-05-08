package com.jecruzv.capstonewl.usecases

import com.jecruzv.local.model.PopularMovie
import com.jecruzv.capstonewl.util.DataState
import com.jecruzv.capstonewl.util.ExploreType
import com.jecruzv.local.dao.MoviesDao
import com.jecruzv.remote.data.dto.toPopularMovie
import com.jecruzv.remote.domain.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get explore content from repository call.
 */
class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository,
    private val moviesDao: MoviesDao
) {
    operator fun invoke(page: Int, exploreType: ExploreType) : Flow<DataState<List<PopularMovie>>> = flow {
        try {
            emit(DataState.Loading())
            var resources = repository.getPopularMovies(page).map { it.toPopularMovie() }
            resources = resources.filterNot { it.poster_path.isNullOrBlank() }
            moviesDao.addPopularMovies(resources)
            emit(DataState.Success(resources))
        } catch (e: HttpException) {
            emit(DataState.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(DataState.Error("Revisa tu internet"))
        }
    }
}