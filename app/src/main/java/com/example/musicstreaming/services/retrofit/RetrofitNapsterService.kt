package com.example.musicstreaming.services.retrofit

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.NapsterResponse
import retrofit2.Call

interface RetrofitNapsterService {
    suspend fun searchTracks(trackName: String): NapsterResponse?


}