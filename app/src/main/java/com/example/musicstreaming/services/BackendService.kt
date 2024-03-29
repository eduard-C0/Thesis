package com.example.musicstreaming.services

import androidx.annotation.WorkerThread
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.ResponseMessage
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.services.retrofit.RetrofitBackendService
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BackendService @Inject constructor(private val retrofitBackendService: RetrofitBackendService){

    @WorkerThread
    fun register(user: User): Boolean? = runBlocking {
        retrofitBackendService.register(user)
    }

    @WorkerThread
    fun login(user: User): ResponseMessage? = runBlocking {
        retrofitBackendService.login(user)
    }

    @WorkerThread
    fun addToFavorites(track: Track): ResponseMessage? = runBlocking {
        retrofitBackendService.addToFavorites(track)
    }

    @WorkerThread
    fun getAllFromFavorites(): List<Track>? = runBlocking {
        retrofitBackendService.getAllTracksFromFavorites()
    }

    @WorkerThread
    fun removeFromFavorites(track: Track): ResponseMessage? = runBlocking {
        retrofitBackendService.removeTrackFromFavorites(track)
    }

    @WorkerThread
    fun getUser(): User? = runBlocking {
        retrofitBackendService.getUser()
    }
}