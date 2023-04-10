package mx.jramon.subias.dbmovieproyect.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import mx.jramon.subias.dbmovieproyect.movies.domain.service.MovieServices
import mx.jramon.subias.dbmovieproyect.utils.Constants.API_KY
import mx.jramon.subias.dbmovieproyect.utils.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


    @Provides
    @Singleton
    fun provideUserAgent(): Interceptor =
        Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url

            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", API_KY).build()
            val request = originalRequest.newBuilder().url(url).build()

            chain.proceed(request)
        }

    @Provides
    @Singleton
    fun provideClient(
        interceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(headerInterceptor).build()


    @Provides
    @Singleton
    fun provideMovie(
        client: OkHttpClient
    ): MovieServices =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieServices::class.java)
}