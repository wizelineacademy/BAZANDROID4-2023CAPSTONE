package com.example.themoviedb.domain.usecase

import com.example.core.model.GenreModel
import com.example.themoviedb.domain.MovieRepository
import com.example.tools.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Flow<ResultWrapper<List<GenreModel>>>> {

    override suspend fun execute(): Flow<ResultWrapper<List<GenreModel>>> {
        return repository.getGenres()
    }
}
