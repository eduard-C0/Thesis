package com.example.musicstreaming.services.retrofit

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.FavoritesResponse
import com.example.musicstreaming.services.dtos.ResponseMessage
import com.example.musicstreaming.services.dtos.Track

interface RetrofitBackendService {
    suspend fun register(user: User): Boolean?
    suspend fun login(user: User): ResponseMessage?
    suspend fun addToFavorites(track: Track): ResponseMessage?
    suspend fun getAllTracksFromFavorites(): List<Track>?
    suspend fun removeTrackFromFavorites(track: Track): ResponseMessage?
    suspend fun getUser(): User?
}