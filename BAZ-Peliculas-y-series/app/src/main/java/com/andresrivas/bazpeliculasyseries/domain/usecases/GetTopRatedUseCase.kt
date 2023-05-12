package com.andresrivas.bazpeliculasyseries.domain.usecases

import com.andresrivas.bazpeliculasyseries.common.domain.UseCase
import com.andresrivas.bazpeliculasyseries.data.repository.MoviesRepository
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.common.utilities.ResultAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : UseCase<Nothing, MoviesPagesModel> {
    override fun execute(params: Nothing?): Flow<ResultAPI<MoviesPagesModel>> {
        return moviesRepository.getMoviesTopRated()
    }
}