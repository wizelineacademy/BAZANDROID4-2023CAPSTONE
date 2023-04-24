package com.andresrivas.bazpeliculasyseries.domain.usecases

import com.andresrivas.bazpeliculasyseries.common.domain.UseCase
import com.andresrivas.bazpeliculasyseries.data.repository.MoviesRepository
import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<Nothing, LatestMoviesModel> {

    override fun execute(params: Nothing?): Flow<ResultAPI<LatestMoviesModel>> {
        return moviesRepository.getLatestMovies()
    }

}