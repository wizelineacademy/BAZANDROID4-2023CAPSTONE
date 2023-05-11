package com.andresrivas.remotedatasource.services.interceptors

import com.andresrivas.remotedatasource.services.Constants.Companion.TheMovieDBAPIKey
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class APIKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", TheMovieDBAPIKey)
            .build()
        return chain.proceed(original.newBuilder().url(url).build())
    }
}
