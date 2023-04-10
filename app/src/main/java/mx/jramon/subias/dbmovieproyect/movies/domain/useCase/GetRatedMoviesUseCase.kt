package mx.jramon.subias.dbmovieproyect.movies.domain.useCase

import io.reactivex.rxjava3.core.Single
import mx.jramon.subias.dbmovieproyect.movies.domain.MovieTvRepository
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity
import javax.inject.Inject

class GetRatedMoviesUseCase @Inject constructor(
    private var repository: MovieTvRepository
) {
    fun invoke(pages:Int):Single<List<MovieEntity>> = repository.getRatedMovie(pages)
}