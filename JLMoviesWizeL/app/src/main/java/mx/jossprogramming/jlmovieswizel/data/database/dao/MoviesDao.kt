package mx.jossprogramming.jlmovieswizel.data.database.dao

import androidx.room.*
import mx.jossprogramming.jlmovieswizel.data.database.entitys.GenreEntity
import mx.jossprogramming.jlmovieswizel.data.database.entitys.MovieEntity
import mx.jossprogramming.jlmovieswizel.data.database.entitys.MovieGenresCrossRef
import mx.jossprogramming.jlmovieswizel.data.database.entitys.MoviesWithGenres

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: GenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(item:List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenresMovie(item:List<MovieGenresCrossRef>)

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getGenresByMovie(id:Int): MoviesWithGenres
}