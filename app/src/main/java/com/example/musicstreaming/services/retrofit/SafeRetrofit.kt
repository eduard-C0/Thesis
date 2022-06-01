package com.example.musicstreaming.services.retrofit

import android.util.Log
import com.example.network.NetworkClient
import java.lang.Exception

internal class SafeRetrofit(private val networkClient: NetworkClient) {
    companion object {
        private const val TAG = "SafeRetrofit"
    }

    fun getRetrofitService(): RetrofitBackendServiceApi? {
        return try {
            networkClient.getService()
        } catch (error: Exception) {
            Log.e(TAG, "Retrofit service couldn't be initialized: $error")
            null
        }
    }

    fun getRetrofitNapsterService(): RetrofitNapsterServiceApi? {
        return try {
            networkClient.getService()
        } catch (error: Exception) {
            Log.e(TAG, "Retrofit service couldn't be initialized: $error")
            null
        }
    }
}