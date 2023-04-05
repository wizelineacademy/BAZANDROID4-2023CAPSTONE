package mx.jramon.subias.dbmovieproyect.movies.domain

import mx.jramon.subias.dbmovieproyect.base.BaseRepository
import mx.jramon.subias.dbmovieproyect.movies.domain.service.MovieServices
import mx.jramon.subias.dbmovieproyect.network.MovieClient.client

class MovieRepository() : BaseRepository<MovieServices>() {

    override var api: MovieServices = client.create(MovieServices::class.java)

    fun getListPopularMovie(pages: Int = 1) = api.getMoviesPopular(pages)

    fun getListPopularTvSerie(pages: Int = 1) = api.getTvSeriesPopular(pages)

    fun getMovieTopRated(pages: Int = 1) = api.getMovieTopRated(pages)

}