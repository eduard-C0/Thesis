package com.example.musicstreaming.services.retrofit

import android.util.Log
import com.example.musicstreaming.services.di.NullableBackendService
import com.example.musicstreaming.services.di.NullableNapsterService
import com.example.musicstreaming.services.dtos.ResponseMessage
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import javax.inject.Inject

internal class RetrofitProxy @Inject constructor( @NullableBackendService private val retrofitBackendServiceApi: RetrofitBackendServiceApi?) :
    InvocationHandler {
    companion object {
        private const val TAG = "RetrofitProxy"
    }

    override fun invoke(proxy: Any?, method: Method?, arguments: Array<out Any>?): Any {
        val service = retrofitBackendServiceApi
        return if (service != null) {
            if (method != null) {
                method.invoke(service, *arguments.orEmpty())
            } else {
                Log.e(TAG, "Method is null. ${method?.name}")
                return ResponseMessage("The serivice was not initialized properly","400")
            }
        } else {
            Log.e(TAG, "Invalid service api!")
            return ResponseMessage("The serivice was not initialized properly","400")
        }
    }
}

internal class RetrofitNapsterProxy @Inject constructor( @NullableNapsterService private val retrofitNapsterServiceApi: RetrofitNapsterServiceApi?) :
    InvocationHandler {
    companion object {
        private const val TAG = "RetrofitNapsterProxy"
    }

    override fun invoke(proxy: Any?, method: Method?, arguments: Array<out Any>?): Any {
        val service = retrofitNapsterServiceApi
        return if (service != null) {
            if (method != null) {
                method.invoke(service, *arguments.orEmpty())
            } else {
                Log.e(TAG, "Method is null. ${method?.name}")
            }
        } else {
            Log.e(TAG, "Invalid service api!")
        }
    }
}

