package com.example.musicstreaming.services

import androidx.annotation.WorkerThread
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.di.DaggerBackendServiceComponent
import com.example.musicstreaming.services.retrofit.RetrofitBackendService
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BackendService {

    @Inject
    internal lateinit var retrofitBackendService: RetrofitBackendService

    fun init(url: String, token: String) {
        DaggerBackendServiceComponent.builder()
            .baseUrl(url)
            .enableLogging(true)
            .build()
            .inject(this)
    }

    @WorkerThread
    fun register(user: User): Boolean? = runBlocking {
        retrofitBackendService.register(user)
    }
}