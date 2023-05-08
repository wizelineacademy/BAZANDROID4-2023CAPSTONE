package mx.jramon.subias.dbmovieproyect.movies.domain

import io.reactivex.rxjava3.core.Single
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieDetails
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity
import mx.jramon.subias.dbmovieproyect.movies.domain.model.TvSerieEntity

interface MovieTvRepository {

     fun getPopularMovies(page:Int): Single<List<MovieEntity>>

     fun getPopularTvSeries(pages:Int): Single<List<TvSerieEntity>>

     fun getRatedMovie(page: Int): Single<List<MovieEntity>>

     fun getDetailMovie(idMovie:Int):Single<MovieDetails>
}