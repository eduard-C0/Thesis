package com.example.musicstreaming.services.retrofit

import android.util.Log
import com.example.musicstreaming.services.dtos.NapsterResponse
import java.lang.Exception

internal class RetrofitNapsterServiceImplementation(private val napsterServiceApi: RetrofitNapsterServiceApi) :
    RetrofitNapsterService {

    companion object{
        private const val TAG = "RetrofitNapsterServiceImplementation"
    }

    override suspend fun searchTracks(trackName: String): NapsterResponse? {
        try {
            val response = napsterServiceApi.searchTracks(trackName)
            Log.d(TAG, "searchTracks Response: $response")
            if(response?.meta?.returnedCount != 0){
                Log.d(TAG, "searchTracks 200")
                return response
            }
            Log.d(TAG, "searchTracks 400 ERROR")
            return null
        } catch (exception: Exception){
            Log.e(TAG, exception.stackTraceToString())
            return null
        }

    }
}