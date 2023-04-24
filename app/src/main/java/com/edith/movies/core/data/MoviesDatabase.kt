package com.edith.movies.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edith.movies.core.data.database.dao.GenderDao
import com.edith.movies.core.data.database.dao.MoviesDao
import com.edith.movies.core.data.database.entity.GenderEntity
import com.edith.movies.core.data.database.entity.MovieEntity


@Database(entities = [MovieEntity::class, GenderEntity::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun movieDao(): MoviesDao

    abstract fun genderDao(): GenderDao

}