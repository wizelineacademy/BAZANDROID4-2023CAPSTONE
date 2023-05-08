package com.jecruzv.capstonewl.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jecruzv.capstonewl.data.model.database.entities.DateConverters
import com.jecruzv.capstonewl.data.model.database.entities.IntegerConverter
import com.jecruzv.capstonewl.domain.model.*
import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.capstonewl.util.Constants

@Annotations("Entregable 2")
@Database(entities = [PopularMovie::class,UpcomingMovie::class,TopRatedMovie::class,MovieDetail::class,Genres::class,MovieGenreCrossRef::class], version = 1)
@TypeConverters(DateConverters::class, IntegerConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao

    companion object {
        private const val DATABASE_NAME = Constants.DB_NAME

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

internal val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE IF EXISTS " + Constants.POPULAR_MOVIE_TABLE + "_old;")
        database.execSQL("ALTER TABLE " + Constants.POPULAR_MOVIE_TABLE + " RENAME TO " + Constants.POPULAR_MOVIE_TABLE + "_old;")
        database.execSQL("INSERT INTO " + Constants.POPULAR_MOVIE_TABLE + " SELECT * FROM " + Constants.POPULAR_MOVIE_TABLE + "_old;")
        database.execSQL("DROP TABLE IF EXISTS " + Constants.POPULAR_MOVIE_TABLE + "_old;")

        database.execSQL("DROP TABLE IF EXISTS " + Constants.UPCOMING_MOVIE_TABLE + "_old;")
        database.execSQL("ALTER TABLE " + Constants.UPCOMING_MOVIE_TABLE + " RENAME TO " + Constants.UPCOMING_MOVIE_TABLE + "_old;")
        database.execSQL("INSERT INTO " + Constants.UPCOMING_MOVIE_TABLE + " SELECT * FROM " + Constants.UPCOMING_MOVIE_TABLE + "_old;")
        database.execSQL("DROP TABLE IF EXISTS " + Constants.UPCOMING_MOVIE_TABLE + "_old;")

        database.execSQL("DROP TABLE IF EXISTS " + Constants.TOP_RATED_MOVIE_TABLE + "_old;")
        database.execSQL("ALTER TABLE " + Constants.TOP_RATED_MOVIE_TABLE + " RENAME TO " + Constants.TOP_RATED_MOVIE_TABLE + "_old;")
        database.execSQL("INSERT INTO " + Constants.TOP_RATED_MOVIE_TABLE + " SELECT * FROM " + Constants.TOP_RATED_MOVIE_TABLE + "_old;")
        database.execSQL("DROP TABLE IF EXISTS " + Constants.TOP_RATED_MOVIE_TABLE + "_old;")

        database.execSQL("DROP TABLE IF EXISTS " + Constants.MOVIE_TABLE + "_old;")
        database.execSQL("ALTER TABLE " + Constants.MOVIE_TABLE + " RENAME TO " + Constants.MOVIE_TABLE + "_old;")
        database.execSQL("INSERT INTO " + Constants.MOVIE_TABLE + " SELECT * FROM " + Constants.MOVIE_TABLE + "_old;")
        database.execSQL("DROP TABLE IF EXISTS " + Constants.MOVIE_TABLE + "_old;")

        database.execSQL("DROP TABLE IF EXISTS " + Constants.GENRES_TABLE + "_old;")
        database.execSQL("ALTER TABLE " + Constants.GENRES_TABLE + " RENAME TO " + Constants.GENRES_TABLE + "_old;")
        database.execSQL("INSERT INTO " + Constants.GENRES_TABLE + " SELECT * FROM " + Constants.GENRES_TABLE + "_old;")
        database.execSQL("DROP TABLE IF EXISTS " + Constants.GENRES_TABLE + "_old;")

        database.execSQL("DROP TABLE IF EXISTS " + Constants.MOVIES_GENRES_TABLE + "_old;")
        database.execSQL("ALTER TABLE " + Constants.MOVIES_GENRES_TABLE + " RENAME TO " + Constants.MOVIES_GENRES_TABLE + "_old;")
        database.execSQL("INSERT INTO " + Constants.MOVIES_GENRES_TABLE + " SELECT * FROM " + Constants.MOVIES_GENRES_TABLE + "_old;")
        database.execSQL("DROP TABLE IF EXISTS " + Constants.MOVIES_GENRES_TABLE + "_old;")
    }
}