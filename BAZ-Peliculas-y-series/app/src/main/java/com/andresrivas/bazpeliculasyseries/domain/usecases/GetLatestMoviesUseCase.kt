package com.andresrivas.bazpeliculasyseries.domain.usecases

import com.andresrivas.bazpeliculasyseries.common.domain.UseCaseRxJava
import com.andresrivas.bazpeliculasyseries.data.repository.MoviesRepository
import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetLatestMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : UseCaseRxJava<Nothing, LatestMoviesModel> {

    override fun execute(params: Nothing?): Single<ResultAPI<LatestMoviesModel>> {
        return moviesRepository.getLatestMovies()
    }
}
