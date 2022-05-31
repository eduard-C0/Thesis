package com.example.musicstreaming.services.retrofit

import android.util.Log
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.toUserDto
import java.lang.Exception

internal class RetrofitBackendServiceImplementation(private val backendServiceApi: RetrofitBackendServiceApi):
    RetrofitBackendService {

    companion object{
        private const val TAG = "RetrofitBackendServiceImplementation"
    }

    override suspend fun register(user: User): Boolean {
        try {
            val response = backendServiceApi.register(user.toUserDto())
            Log.d(TAG, "Register Response: $response")
            if(response.code == "200"){
                Log.d(TAG, "Register 200")
                return true
            }
            Log.d(TAG, "Register 400 ERROR")
            return false
        } catch (exception: Exception){
            Log.e(TAG, exception.stackTraceToString())
            return false
        }

    }

    override suspend fun login(user: User): Boolean {
        try {
            val response = backendServiceApi.login(user.toUserDto())
            Log.d(TAG, "Login Response: $response")
            if(response.code == "200"){
                Log.d(TAG, "Login 200")
                return true
            }
            Log.d(TAG, "Login 400 ERROR")
            return false
        } catch (exception: Exception){
            Log.e(TAG, exception.stackTraceToString())
            return false
        }
    }
}