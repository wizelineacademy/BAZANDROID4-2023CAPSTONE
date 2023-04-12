package mx.jossprogramming.databasemovies.repository

import mx.jossprogramming.databasemovies.dao.MoviesDao
import mx.jossprogramming.databasemovies.entitys.GenreEntity
import mx.jossprogramming.databasemovies.entitys.MovieEntity
import mx.jossprogramming.databasemovies.entitys.MovieGenresCrossRef
import mx.jossprogramming.databasemovies.entitys.MoviesWithGenres
import javax.inject.Inject

class MoviesRespository @Inject constructor(
    private val moviesDao: MoviesDao
) {
    suspend fun insertGenre(genreEntity: GenreEntity){
        moviesDao.insertGenre(genreEntity)
    }

    suspend fun insertMovie(movieEntity:ArrayList<MovieEntity>){
        moviesDao.insertMovie(movieEntity)
    }

    suspend fun insertGenresMovie(genresCrossRef: List<MovieGenresCrossRef>){
        moviesDao.insertGenresMovie(genresCrossRef)
    }

    suspend fun getMovieWithGenres(idMovie:Int): MoviesWithGenres {
        return moviesDao.getGenresByMovie(idMovie)
    }
}