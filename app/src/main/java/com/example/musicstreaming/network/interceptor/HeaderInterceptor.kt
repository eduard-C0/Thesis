package com.example.musicstreaming.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

object HeaderInterceptor : Interceptor {
    private const val AUTHORIZATION = "app-auth"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader(AUTHORIZATION, "")
            .build()
        return chain.proceed(request)
    }
}