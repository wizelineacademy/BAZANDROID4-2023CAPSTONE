package com.example.network

import com.example.tools.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                OkHttpClient.Builder()
                    .addInterceptor(this)
                    .addInterceptor(ApiKeyInterceptor(BuildConfig.MOVIES_API_KEY))
                    .build()
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(T::class.java)
        }

        suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): ResultWrapper<T> {
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
                            ResultWrapper.Error(0, "Something went wrong")
                        }
                    }
                }
            }
        }
    }
}