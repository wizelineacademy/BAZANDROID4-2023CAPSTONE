package com.example.themoviedb.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        NowPlayingLocal::class,
        LatestLocal::class,
        TopRatedLocal::class
               ],
    version = 1, exportSchema = false
)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun nowPlayingDao(): NowPlayingDao
    abstract fun latestDao(): LatestDao
    abstract fun topRatedDao(): TopRatedDao
}