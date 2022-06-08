package com.example.musicstreaming.services.retrofit

import android.util.Log
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.FavoritesResponse
import com.example.musicstreaming.services.dtos.ResponseMessage
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.services.toTracks
import com.example.musicstreaming.services.toUserDto
import java.lang.Exception

internal class RetrofitBackendServiceImplementation(private val backendServiceApi: RetrofitBackendServiceApi):
    RetrofitBackendService {

    companion object{
        private const val TAG = "RetrofitBackendServiceImplementation"
    }

    override suspend fun register(user: User): Boolean {
        try {
            val response = backendServiceApi.register(user.toUserDto())
            Log.d(TAG, "Register Response: $response")
            if(response?.code == "200"){
                Log.d(TAG, "Register 200")
                return true
            }
            Log.d(TAG, "Register 400 ERROR")
            return false
        } catch (exception: Exception){
            Log.e(TAG, exception.stackTraceToString())
            return false
        }

    }

    override suspend fun login(user: User): ResponseMessage? {
        return try {
            val response = backendServiceApi.login(user.toUserDto())
            Log.d(TAG, "Login Response: $response")
            response
        } catch (exception: Exception){
            Log.e(TAG, exception.stackTraceToString())
            ResponseMessage("Error occured", "400")
        }
    }

    override suspend fun addToFavorites(track: Track): ResponseMessage? {
        return try {
            val response = backendServiceApi.addToFavorites(track)
            Log.d(TAG, "Add to favorites: $response")
            response
        } catch (exception: Exception){
            Log.e(TAG, exception.stackTraceToString())
            null
        }
    }

    override suspend fun getAllTracksFromFavorites(): List<Track>? {
        return try {
            val response = backendServiceApi.getAllTracksFromFavorites()
            Log.d(TAG, "Get All Tracks From Favorites: $response")
            response
        } catch (exception: Exception){
            Log.e(TAG, exception.stackTraceToString())
            null
        }
    }

    override suspend fun removeTrackFromFavorites(track: Track): ResponseMessage? {
        return try {
            val response = backendServiceApi.removeTrackFromFavorites(track)
            Log.d(TAG, "Remove Track From Favorites: $response")
            response
        } catch (exception: Exception){
            Log.e(TAG, exception.stackTraceToString())
            null
        }
    }

    override suspend fun getUser(): User? {
        return try {
            val response = backendServiceApi.getUser()
            Log.d(TAG, "Remove Track From Favorites: $response")
            response
        } catch (exception: Exception){
            Log.e(TAG, exception.stackTraceToString())
            null
        }
    }


}