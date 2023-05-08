package com.jecruzv.capstonewl.domain.usecases

import com.jecruzv.capstonewl.data.remote.dto.toVideo
import com.jecruzv.capstonewl.domain.model.Video
import com.jecruzv.capstonewl.domain.repository.MoviesRepository
import com.jecruzv.capstonewl.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use-case to get videos from repository call.
 */
class GetVideosUseCase @Inject constructor(
    private val repository: MoviesRepository) {
    operator fun invoke(mediaId: Int): Flow<DataState<List<Video>>> = flow {
        try {
            emit(DataState.Loading())
            val videos = repository.getMovieVideos(mediaId).map { it.toVideo() }
            emit(DataState.Success(videos))
        } catch (e: HttpException) {
            emit(DataState.Error(e.localizedMessage ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(DataState.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}