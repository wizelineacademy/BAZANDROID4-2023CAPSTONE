package mx.jramon.subias.dbmovieproyect.movies.domain.useCase

import io.reactivex.rxjava3.core.Single
import mx.jramon.subias.dbmovieproyect.movies.domain.MovieTvRepository
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieDetails
import javax.inject.Inject

class GetDetailsMovieUseCase @Inject constructor(
    private val repository: MovieTvRepository
) {

    fun invoke(idMovie:Int): Single<MovieDetails> = repository.getDetailMovie(idMovie)
}