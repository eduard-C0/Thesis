package com.example.musicstreaming.network.interceptor

import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class HeaderInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {
    companion object {
        private const val AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var token = sharedPreferences.getString("Token", "")
        Log.d("HeaderInterceptor",token.toString())
        if(token == null){
            token = ""
        }
        Log.d("HeaderInterceptor",token.toString())
        val request: Request = chain.request()
            .newBuilder()
            .addHeader(AUTHORIZATION, token)
            .build()
        return chain.proceed(request)
    }

}