package mx.jossprogramming.jlmovieswizel.data.database.repository

import mx.jossprogramming.jlmovieswizel.data.database.dao.MoviesDao
import mx.jossprogramming.jlmovieswizel.data.database.entitys.GenreEntity
import mx.jossprogramming.jlmovieswizel.data.database.entitys.MovieEntity
import mx.jossprogramming.jlmovieswizel.data.database.entitys.MovieGenresCrossRef
import mx.jossprogramming.jlmovieswizel.data.database.entitys.MoviesWithGenres
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