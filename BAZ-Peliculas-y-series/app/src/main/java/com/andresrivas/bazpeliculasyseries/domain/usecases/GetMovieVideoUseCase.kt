package com.andresrivas.bazpeliculasyseries.domain.usecases

import com.andresrivas.bazpeliculasyseries.common.domain.UseCase
import com.andresrivas.bazpeliculasyseries.data.repository.MoviesRepository
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieVideoUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<GetMovieVideoUseCase.Params, MoviesVideoModel> {
    override fun execute(params: Params?): Flow<ResultAPI<MoviesVideoModel>> {
        return params?.let {
            moviesRepository.getMoviesVideo(it.movieId)
        } ?: moviesRepository.getMoviesVideo("")
    }

    data class Params(
        val movieId: String
    )
}
