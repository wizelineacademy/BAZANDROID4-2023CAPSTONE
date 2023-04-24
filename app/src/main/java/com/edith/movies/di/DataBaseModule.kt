package com.edith.movies.di

import android.content.Context
import androidx.room.Room
import com.edith.movies.core.data.MoviesDatabase
import com.edith.movies.core.data.database.dao.GenderDao
import com.edith.movies.core.data.database.dao.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext applicationContext: Context): MoviesDatabase{
        return Room.databaseBuilder(
            applicationContext,
            MoviesDatabase::class.java, "movies_db").build()

    }

    @Singleton
    @Provides
    fun provideMovieDao(db: MoviesDatabase): MoviesDao {
        return db.movieDao()

    }

    @Singleton
    @Provides
    fun provideGenderDao(db: MoviesDatabase): GenderDao {
        return db.genderDao()
    }

}