package mx.jramon.subias.dbmovieproyect.movies.data.dataSource.local

import io.reactivex.rxjava3.core.Single
import mx.jramon.subias.dbmovieproyect.movies.data.dataSource.local.dao.MovieDao
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
   private val movieDao: MovieDao
) {

    fun insertMovieEntity(movieList:List<MovieEntity>) =
        movieDao.insertMovieEntity(movieList)

    fun getMovies(): Single<List<MovieEntity>> = movieDao.getMovies()

}