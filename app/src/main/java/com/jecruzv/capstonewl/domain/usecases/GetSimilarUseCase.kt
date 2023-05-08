package com.jecruzv.capstonewl.domain.usecases

import com.jecruzv.capstonewl.data.remote.dto.toPopularMovie
import com.jecruzv.capstonewl.domain.model.PopularMovie
import com.jecruzv.capstonewl.domain.repository.MoviesRepository
import com.jecruzv.capstonewl.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get similar movies/shows from repository call.
 */
class GetSimilarUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(mediaId: Int,page:Int) : Flow<DataState<List<PopularMovie>>> = flow {
        try {
            emit(DataState.Loading())
            val resources = repository.getSimilarMovies(mediaId,page).map { it.toPopularMovie() }
            emit(DataState.Success(resources))
        } catch (e: HttpException) {
            emit(DataState.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(DataState.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}