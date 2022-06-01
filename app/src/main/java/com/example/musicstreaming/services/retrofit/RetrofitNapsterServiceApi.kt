package com.example.musicstreaming.services.retrofit

import com.example.musicstreaming.services.dtos.NapsterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RetrofitNapsterServiceApi {

    @GET("v2.2/search")
    suspend fun searchTracks(@Query("query") trackName: String, @Query("type") type: String = "track"): NapsterResponse?

}