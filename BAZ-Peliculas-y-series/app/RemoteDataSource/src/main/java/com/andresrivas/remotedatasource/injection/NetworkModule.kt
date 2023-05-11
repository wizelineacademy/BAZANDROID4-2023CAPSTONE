package com.andresrivas.remotedatasource.injection

import com.andresrivas.remotedatasource.services.APIService
import com.andresrivas.remotedatasource.services.datasource.MoviesDataSource
import com.andresrivas.remotedatasource.services.interceptors.APIKeyInterceptor
import com.andresrivas.remotedatasource.services.moviesapi.MoviesRemoteDataSource
import com.andresrivas.remotedatasource.utilities.Constants.Companion.TheMovieDBBaseURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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
            .baseUrl(TheMovieDBBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @MoviesRepositoryRemote
    @Singleton
    @Provides
    fun provideMoviesRepositoryRemote(retrofit: Retrofit): MoviesDataSource {
        return MoviesRemoteDataSource(retrofit.create(APIService::class.java))
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesRepositoryRemote

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesRepositoryLocal
