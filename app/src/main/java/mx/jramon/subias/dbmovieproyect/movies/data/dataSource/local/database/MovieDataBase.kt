package mx.jramon.subias.dbmovieproyect.movies.data.dataSource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.jramon.subias.dbmovieproyect.movies.data.dataSource.local.dao.MovieDao
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

}