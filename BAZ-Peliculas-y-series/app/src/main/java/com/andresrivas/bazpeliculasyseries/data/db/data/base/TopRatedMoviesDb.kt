package com.andresrivas.bazpeliculasyseries.data.db.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andresrivas.bazpeliculasyseries.data.db.dao.MovieDao
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel

@Database(
    entities = [MovieModel::class],
    version = 1,
)
abstract class TopRatedMoviesDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
