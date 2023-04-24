package mx.jramon.subias.dbmovieproyect.movies.data.repositories

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import mx.jramon.subias.dbmovieproyect.movies.data.dataSource.remote.MovieRemoteDataSource
import mx.jramon.subias.dbmovieproyect.movies.data.dataSource.local.MovieLocalDataSource
import mx.jramon.subias.dbmovieproyect.movies.domain.MovieTvRepository
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieDetails
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity
import mx.jramon.subias.dbmovieproyect.movies.domain.model.TvSerieEntity
import mx.jramon.subias.dbmovieproyect.network.NetworkMonitor
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val networkMonitor: NetworkMonitor
) : MovieTvRepository {

    override fun getPopularMovies(page: Int): Single<List<MovieEntity>> {
        return if(networkMonitor.isOnline()){
            movieRemoteDataSource
                .getPopularMovie(page)
                .map { it.results }
                .doOnSuccess {
                    movieLocalDataSource.insertMovieEntity(it)
                }.subscribeOn(Schedulers.io())
        }else
            movieLocalDataSource.getMovies()
    }


    override fun getPopularTvSeries(pages: Int): Single<List<TvSerieEntity>> {
       return movieRemoteDataSource.getPopularTvSeries(pages).map { it.listSeries }.subscribeOn(Schedulers.io())
    }

    override fun getRatedMovie(page: Int): Single<List<MovieEntity>> {
        return movieRemoteDataSource.getRatedMovies(page).map { it.results }.subscribeOn(Schedulers.io())
    }

    override fun getDetailMovie(idMovie: Int): Single<MovieDetails> {
        return movieRemoteDataSource.getDetailMovie(idMovie).subscribeOn(Schedulers.io())
    }
}