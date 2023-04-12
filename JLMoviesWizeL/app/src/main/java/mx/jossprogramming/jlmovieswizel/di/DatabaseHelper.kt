package mx.jossprogramming.jlmovieswizel.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.jossprogramming.databasemovies.AppDatabase
import mx.jossprogramming.databasemovies.dao.MoviesDao

import javax.inject.Singleton

//TODO CONFIGURACIÓN DE ROOM A TRAVÉS DE HILT
@Module
@InstallIn(SingletonComponent::class)
object DatabaseHelper {
    @Provides
    @Singleton
    fun provideMoviesDao(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.moviesDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context):AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "MoviesDatabase"
        ).build()
    }
}