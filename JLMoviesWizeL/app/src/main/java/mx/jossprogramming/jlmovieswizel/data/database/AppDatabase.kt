package mx.jossprogramming.jlmovieswizel.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.jossprogramming.jlmovieswizel.data.database.dao.MoviesDao
import mx.jossprogramming.jlmovieswizel.data.database.entitys.GenreEntity
import mx.jossprogramming.jlmovieswizel.data.database.entitys.MovieEntity
import mx.jossprogramming.jlmovieswizel.data.database.entitys.MovieGenresCrossRef

@Database(entities = [GenreEntity::class, MovieEntity::class, MovieGenresCrossRef::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}