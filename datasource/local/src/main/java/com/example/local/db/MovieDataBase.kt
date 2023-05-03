package com.example.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.dao.GenresDao
import com.example.local.dao.LatestDao
import com.example.local.dao.NowPlayingDao
import com.example.local.entity.GenreLocal
import com.example.local.entity.LatestLocal
import com.example.local.entity.NowPlayingLocal
import com.example.local.dao.TopRatedDao
import com.example.local.entity.TopRatedLocal
import com.example.tools.Converters

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