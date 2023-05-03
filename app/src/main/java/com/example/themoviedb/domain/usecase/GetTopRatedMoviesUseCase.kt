package com.example.themoviedb.domain.usecase

import com.example.core.model.MovieModel
import com.example.themoviedb.domain.MovieRepository
import com.example.tools.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
): BaseUseCase<Flow<ResultWrapper<List<MovieModel>>>> {

    override suspend fun execute(): Flow<ResultWrapper<List<MovieModel>>> {
        return repository.getTopRated()
    }
}