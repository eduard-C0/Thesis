package com.example.musicstreaming.services

import androidx.annotation.WorkerThread
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.NapsterResponse
import com.example.musicstreaming.services.retrofit.RetrofitNapsterService
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NapsterService @Inject constructor(private val retrofitNapsterService: RetrofitNapsterService){

    @WorkerThread
    fun searchTracks(trackName: String): NapsterResponse? = runBlocking {
        retrofitNapsterService.searchTracks(trackName)
    }
}