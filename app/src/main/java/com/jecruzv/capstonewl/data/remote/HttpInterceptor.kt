package com.jecruzv.capstonewl.data.remote

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val sign: String = if (TextUtils.isEmpty(request.url.encodedQuery)) "?" else "&"
        val newRequest: Request.Builder = request.newBuilder().url(request.url.toString())// + sign + "api_key=" + "e3b6ae0d58f7a38f4602d043aa69e2c3")
        return chain.proceed(newRequest.build())
    }
}