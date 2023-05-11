package com.andresrivas.localdatasource.db.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.localdatasource.db.dao.MovieDao

@Database(
    entities = [MovieModel::class],
    version = 1,
)
abstract class NowPlayingMoviesDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
