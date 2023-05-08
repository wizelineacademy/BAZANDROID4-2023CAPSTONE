package mx.jramon.subias.dbmovieproyect.movies.domain.useCase

import io.reactivex.rxjava3.core.Single
import mx.jramon.subias.dbmovieproyect.movies.domain.MovieTvRepository
import mx.jramon.subias.dbmovieproyect.movies.domain.model.TvSerieEntity
import javax.inject.Inject

class GetPopularTvSeriesUseCase @Inject constructor(
    private var repository: MovieTvRepository
) {
    fun invoke(page:Int): Single<List<TvSerieEntity>> = repository.getPopularTvSeries(page)
}