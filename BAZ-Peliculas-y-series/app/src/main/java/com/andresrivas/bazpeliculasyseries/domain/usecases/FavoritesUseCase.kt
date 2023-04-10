package com.andresrivas.bazpeliculasyseries.domain.usecases

import com.andresrivas.bazpeliculasyseries.common.domain.UseCase
import com.andresrivas.bazpeliculasyseries.data.repository.MoviesRepository
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<FavoritesUseCase.Params, MoviesVideoModel> {
    override fun execute(params: Params?): Flow<ResultAPI<MoviesVideoModel>> {
        return params?.let {
            moviesRepository.getMoviesVideo(it.movieId)
        } ?: moviesRepository.getMoviesVideo("")
    }

    fun getFavoriteMovies(): Flow<ResultAPI<List<MovieModel>>> {
        return moviesRepository.getFavorites()
    }

    fun saveFavoritesIfNoExist(movieModel: List<MovieModel>): Flow<ResultAPI<List<MovieModel>>>  {
        return moviesRepository.saveFavoritesIfNoExist(movieModel)
    }

    fun isFavorite(movie: MovieModel): Flow<ResultAPI<Boolean>>  {
        return moviesRepository.isFavorite(movie)
    }

    data class Params(
        val movieId: String
    )
}