package com.example.themoviedb.di

import android.content.Context
import androidx.room.Room
import com.example.themoviedb.data.datasource.local.*
import com.example.themoviedb.data.datasource.remote.MovieRemoteDataSource
import com.example.themoviedb.data.datasource.remote.MovieRemoteDataSourceImpl
import com.example.themoviedb.di.repository.MovieRepositoryImpl
import com.example.themoviedb.data.service.MovieApiService
import com.example.themoviedb.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideMovieApiService(): MovieApiService {
        return MovieApiService.Builder().build()
    }

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(
        apiService: MovieApiService
    ): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MovieDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDataBase::class.java,
            "Movies.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNowPlayingDao(
        movieDataBase: MovieDataBase
    ): NowPlayingDao{
        return movieDataBase.nowPlayingDao()
    }

    @Provides
    @Singleton
    fun provideLatestDao(
        movieDataBase: MovieDataBase
    ): LatestDao {
        return movieDataBase.latestDao()
    }

    @Provides
    @Singleton
    fun provideTopRatedDao(
        movieDataBase: MovieDataBase
    ): TopRatedDao {
        return movieDataBase.topRatedDao()
    }

    @Provides
    @Singleton
    fun provideGenresDao(
        movieDataBase: MovieDataBase
    ): GenresDao {
        return movieDataBase.genresDao()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        localDataSource: MovieLocalDataSource,
        remoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            localDataSource,
            remoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(
        nowPlayingDao: NowPlayingDao,
        latestDao: LatestDao,
        topRatedDao: TopRatedDao,
        genresDao: GenresDao
    ): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(
            nowPlayingDao,
            latestDao,
            topRatedDao,
            genresDao
        )
    }
}