package com.andresrivas.bazpeliculasyseries.injection

import com.andresrivas.bazpeliculasyseries.data.db.MoviesLocalDataSourceImpl
import com.andresrivas.bazpeliculasyseries.data.db.data.base.FavoriteMoviesDb
import com.andresrivas.bazpeliculasyseries.data.db.data.base.MovieDb
import com.andresrivas.bazpeliculasyseries.data.repository.datasource.MoviesDataSource
import com.andresrivas.bazpeliculasyseries.data.repository.datasource.MoviesLocalDataSource
import com.andresrivas.bazpeliculasyseries.data.services.APIService
import com.andresrivas.bazpeliculasyseries.data.services.interceptors.APIKeyInterceptor
import com.andresrivas.bazpeliculasyseries.data.services.moviesapi.MoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(apiKeyInterceptor: APIKeyInterceptor): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @MoviesRepositoryRemote
    @Singleton
    @Provides
    fun provideMoviesRepositoryRemote(retrofit: Retrofit): MoviesDataSource {
        return MoviesRemoteDataSource(retrofit.create(APIService::class.java))
    }

    @Singleton
    @Provides
    fun provideMoviesLocalDataSource(moviesDb: MovieDb, favoritesDb: FavoriteMoviesDb): MoviesLocalDataSource {
        return MoviesLocalDataSourceImpl(moviesDb, favoritesDb)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesRepositoryRemote

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesRepositoryLocal