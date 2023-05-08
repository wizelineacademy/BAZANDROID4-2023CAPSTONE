package com.jecruzv.capstonewl.di

import android.content.Context
import androidx.room.Room
import com.jecruzv.capstonewl.data.model.MIGRATION_1_2
import com.jecruzv.capstonewl.data.model.MoviesDatabase
import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.capstonewl.util.Constants
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
        Room.databaseBuilder(ctx, MoviesDatabase::class.java, Constants.DB_NAME)
            //.addMigrations(MIGRATION_1_2)
            .build()

    @Singleton
    @Provides
    fun provideDao(database: MoviesDatabase) = database.movieDao()

}