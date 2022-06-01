package com.example.musicstreaming.music.musicstreaming.home

import com.example.musicstreaming.services.NapsterService
import com.example.musicstreaming.services.dtos.NapsterResponse
import com.example.musicstreaming.services.dtos.TopArtist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopArtistUseCase @Inject constructor(private val napsterService: NapsterService) {

    suspend operator fun invoke(): TopArtist? {
        return try {
            withContext(Dispatchers.IO) {
                val resp = napsterService.getTopArtists()
                resp
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }
}