package mx.jramon.subias.dbmovieproyect.movies.domain.useCase

import io.reactivex.rxjava3.core.Single
import mx.jramon.subias.dbmovieproyect.movies.domain.MovieTvRepository
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity
import javax.inject.Inject

class GetMoviePopularUseCase @Inject constructor(
    private var repository : MovieTvRepository
) {
    fun invoke(page:Int): Single<List<MovieEntity>> = repository.getPopularMovies(page)
}