package com.nmartindev.fortnite.data.okhttp

import com.nmartindev.fortnite.AppConstants.Http.AUTHORIZATION_HEADER
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(AUTHORIZATION_HEADER, apiKey)
            .build()
        return chain.proceed(request)
    }
}