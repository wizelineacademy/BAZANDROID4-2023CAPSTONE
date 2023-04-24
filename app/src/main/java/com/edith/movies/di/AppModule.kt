package com.edith.movies.di

import com.edith.movies.core.service.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson =
        GsonBuilder()
            .create()

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit.Builder): ApiService =
        retrofit
            .build()
            .create(ApiService::class.java)


    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
        return client.build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun providesInterceptor() =
        HttpLoggingInterceptor().apply {
            setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        }
}