package mx.jossprogramming.databasemovies

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.jossprogramming.databasemovies.dao.MoviesDao
import mx.jossprogramming.databasemovies.entitys.GenreEntity
import mx.jossprogramming.databasemovies.entitys.MovieEntity
import mx.jossprogramming.databasemovies.entitys.MovieGenresCrossRef

@Database(entities = [GenreEntity::class, MovieEntity::class, MovieGenresCrossRef::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}