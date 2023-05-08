package mx.jramon.subias.dbmovieproyect.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.jramon.subias.dbmovieproyect.movies.data.dataSource.local.dao.MovieDao
import mx.jramon.subias.dbmovieproyect.movies.data.dataSource.local.database.MovieDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context:Context): MovieDataBase =
        Room.databaseBuilder(
            context,
            MovieDataBase::class.java,
            "mx.jramon.subias.dbmovieproyect.movie_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()


    @Provides
    @Singleton
    fun provideMovieDao(moviesDatabase: MovieDataBase): MovieDao = moviesDatabase.movieDao()

}