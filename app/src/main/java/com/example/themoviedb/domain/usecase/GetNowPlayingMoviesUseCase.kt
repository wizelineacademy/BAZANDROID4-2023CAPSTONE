package com.example.themoviedb.domain.usecase

import com.example.themoviedb.domain.MovieModel
import com.example.themoviedb.domain.MovieRepository
import com.example.themoviedb.util.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
): BaseUseCase<Flow<ResultWrapper<List<MovieModel>>>> {

    override suspend fun execute(): Flow<ResultWrapper<List<MovieModel>>> {
        return repository.getNowPlaying()
    }
}