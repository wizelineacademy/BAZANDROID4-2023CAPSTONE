package com.example.themoviedb.network

import com.example.themoviedb.util.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class APIServiceAutoBuilder<T> {
    abstract fun build(): T

    companion object {
        inline fun <reified T>getInstanceRetrofit(baseUrl: String): T {
            val okHttpClient = HttpLoggingInterceptor().run {
                level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(this)
                    .addInterceptor(ApiKeyInterceptor("61d7cb2732da6def41add30787cfd905"))
                    .build()
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(T::class.java)
        }

        suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T> {
            return withContext(dispatcher) {
                try {
                    ResultWrapper.Success(apiCall.invoke())
                } catch (throwable: Throwable) {
                    when (throwable) {
                        //is IOException -> ResultWrapper.NetworkError
                        is HttpException -> {
                            val code = throwable.code()
                            ResultWrapper.Error(code, throwable.message())
                        }
                        else -> {
                            ResultWrapper.Error(0, "")
                        }
                    }
                }
            }
        }
    }
}