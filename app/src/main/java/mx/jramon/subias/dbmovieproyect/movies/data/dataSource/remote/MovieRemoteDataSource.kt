package mx.jramon.subias.dbmovieproyect.movies.data.dataSource.remote

import mx.jramon.subias.dbmovieproyect.movies.data.dataSource.remote.MovieServices
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val api: MovieServices
) {
    fun getPopularMovie(page:Int) = api.getMoviesPopular(page)

    fun getPopularTvSeries(page:Int) = api.getTvSeriesPopular(page)

    fun getRatedMovies(page:Int) = api.getMovieTopRated(page)

    fun getDetailMovie(idMovie:Int) = api.getMovieInfo(idMovie)
}