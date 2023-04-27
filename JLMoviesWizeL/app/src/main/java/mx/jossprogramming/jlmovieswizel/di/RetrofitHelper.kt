package mx.jossprogramming.jlmovieswizel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import mx.jossprogramming.jlmovieswizel.common.Constantes
import mx.jossprogramming.jlmovieswizel.interceptors.MovieInterceptor
import mx.jossprogramming.remote.network.MovieApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Módulo de inyección para Retrofit
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): MovieApiClient {
        return builder
            .build()
            .create(MovieApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(MovieInterceptor()).build()
        return Retrofit.Builder()
            .baseUrl(Constantes.URL_BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
    }
}