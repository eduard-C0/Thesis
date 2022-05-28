package com.example.musicstreaming.services.retrofit

import android.util.Log
import com.example.musicstreaming.services.di.NullableBackendService
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import javax.inject.Inject

//TODO Add nullable service scope
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
                //TODO return a mocked response
                Log.e(TAG, "Method is null. ${method?.name}")
                return 1
            }
        } else {
            //TODO return a mocked response
            Log.e(TAG, "Invalid service api!")
        }
    }

}