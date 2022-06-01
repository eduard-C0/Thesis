package com.example.musicstreaming.services.retrofit

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.NapsterResponse
import com.example.musicstreaming.services.dtos.TopArtist
import com.example.musicstreaming.services.dtos.TopTrack
import retrofit2.Call

interface RetrofitNapsterService {
    suspend fun searchTracks(trackName: String): NapsterResponse?
    suspend fun getTopArtists(): TopArtist?
    suspend fun getTopTracks(artistId: String): TopTrack?
}