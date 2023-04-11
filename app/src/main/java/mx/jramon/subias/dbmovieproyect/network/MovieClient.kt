package mx.jramon.subias.dbmovieproyect.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import mx.jramon.subias.dbmovieproyect.movies.domain.service.MovieServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieClient {

    val BASE_URL = "https://api.themoviedb.org/3/"
    val API_KY = "5b70b9f5f2551ae0aae4cf024ff683bd"

    private val okHttpClientBuilder = OkHttpClient.Builder().apply {
        addInterceptor(RequestInterceptor())
    }

    private val okHttpClient = okHttpClientBuilder.build()

     val client: MovieServices = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieServices::class.java)
}