package com.jecruzv.capstonewl.di

import android.content.Context
import androidx.room.Room
import com.jecruzv.capstonewl.data.database.MoviesDatabase
import com.jecruzv.capstonewl.util.Annotations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Annotations("Entregable 2")
@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext ctx: Context) =
        Room.databaseBuilder(ctx, MoviesDatabase::class.java, "TMDBDatabase").build()

    @Singleton
    @Provides
    fun provideDao(database: MoviesDatabase) = database.movieDao()

}