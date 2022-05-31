package com.example.musicstreaming.services.retrofit

import com.example.musicstreaming.commonVO.User

interface RetrofitBackendService {
    suspend fun register(user: User): Boolean?
    suspend fun login(user: User): Boolean?
}