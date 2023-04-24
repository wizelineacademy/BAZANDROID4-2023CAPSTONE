package com.example.themoviedb.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.themoviedb.util.room.Converters

@Database(
    entities = [
        NowPlayingLocal::class,
        LatestLocal::class,
        TopRatedLocal::class,
        GenreLocal::class,
    ],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun nowPlayingDao(): NowPlayingDao
    abstract fun latestDao(): LatestDao
    abstract fun topRatedDao(): TopRatedDao
    abstract fun genresDao(): GenresDao
}