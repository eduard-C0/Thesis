package com.example.musicstreaming.services.retrofit

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.ResponseMessage

interface RetrofitBackendService {
    suspend fun register(user: User): Boolean?
    suspend fun login(user: User): ResponseMessage?
}