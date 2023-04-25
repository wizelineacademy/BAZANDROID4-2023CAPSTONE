package com.jecruzv.capstonewl.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jecruzv.capstonewl.data.database.dao.MoviesDao
import com.jecruzv.capstonewl.data.database.entities.PopularMoviesDB
import com.jecruzv.capstonewl.util.Annotations

@Annotations("Entregable 2")
@Database(entities = [PopularMoviesDB::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}