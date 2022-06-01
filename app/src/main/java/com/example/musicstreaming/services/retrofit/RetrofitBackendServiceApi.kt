package com.example.musicstreaming.services.retrofit

import com.example.musicstreaming.services.dtos.ResponseMessage
import com.example.musicstreaming.services.dtos.UserDto
import retrofit2.http.Body
import retrofit2.http.POST

internal const val USER_URL = "/user"

internal interface RetrofitBackendServiceApi {
    @POST("$USER_URL/createUser")
    suspend fun register(@Body userDto: UserDto): ResponseMessage

    @POST("$USER_URL/login")
    suspend fun login(@Body userDto: UserDto): ResponseMessage
}