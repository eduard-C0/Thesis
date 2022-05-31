package com.example.musicstreaming.services

import androidx.annotation.WorkerThread
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.retrofit.RetrofitBackendService
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BackendService @Inject constructor(private val retrofitBackendService: RetrofitBackendService){

    @WorkerThread
    fun register(user: User): Boolean? = runBlocking {
        retrofitBackendService.register(user)
    }

    @WorkerThread
    fun login(user: User): Boolean? = runBlocking {
        retrofitBackendService.login(user)
    }
}