package mx.jramon.subias.dbmovieproyect.movies.domain

import mx.jramon.subias.dbmovieproyect.network.MovieClient.client

class MovieRepository {

    fun getListPopularMovie(pages: Int = 1) = client.getMoviesPopular(pages)

    fun getListPopularTvSerie(pages: Int = 1) = client.getTvSeriesPopular(pages)

    fun getMovieTopRated(pages: Int = 1) = client.getMovieTopRated(pages)

}