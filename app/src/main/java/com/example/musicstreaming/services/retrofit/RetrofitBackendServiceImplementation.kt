package com.example.musicstreaming.services.retrofit

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.toUserDto

internal class RetrofitBackendServiceImplementation(private val backendServiceApi: RetrofitBackendServiceApi):
    RetrofitBackendService {
    override suspend fun register(user: User): Boolean {
        val response = backendServiceApi.register(user.toUserDto())
        if(response.code == "200"){
            return true
        }
        return false
    }
}