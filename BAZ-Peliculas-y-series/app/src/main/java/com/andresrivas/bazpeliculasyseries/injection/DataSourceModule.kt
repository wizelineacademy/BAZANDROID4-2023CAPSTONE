package com.andresrivas.bazpeliculasyseries.injection

import com.andresrivas.localdatasource.datasource.MoviesLocalDataSource
import com.andresrivas.localdatasource.datasource.MoviesLocalDataSourceImpl
import com.andresrivas.localdatasource.db.data.base.FavoriteMoviesDb
import com.andresrivas.localdatasource.db.data.base.NowPlayingMoviesDb
import com.andresrivas.localdatasource.db.data.base.TopRatedMoviesDb
import com.andresrivas.remotedatasource.services.APIService
import com.andresrivas.remotedatasource.services.datasource.MoviesDataSource
import com.andresrivas.remotedatasource.services.moviesapi.MoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @MoviesRepositoryRemote
    @Singleton
    @Provides
    fun provideMoviesRepositoryRemote(retrofit: Retrofit): MoviesDataSource {
        return MoviesRemoteDataSource(retrofit.create(APIService::class.java))
    }

    @Singleton
    @Provides
    fun provideNowPlayingMoviesLocalDataSource(
        nowPlayingMoviesDb: NowPlayingMoviesDb,
        topRatedMoviesDb: TopRatedMoviesDb,
        favoritesDb: FavoriteMoviesDb,
    ): MoviesLocalDataSource {
        return MoviesLocalDataSourceImpl(nowPlayingMoviesDb, topRatedMoviesDb, favoritesDb)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesRepositoryRemote

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesRepositoryLocal
