package com.example.musicstreaming.music.musicstreaming.home

import com.example.musicstreaming.services.NapsterService
import com.example.musicstreaming.services.dtos.TopArtist
import com.example.musicstreaming.services.dtos.TopTrack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopTrackUseCase @Inject constructor(private val napsterService: NapsterService) {

    suspend operator fun invoke(artistId: String): TopTrack? {
        return try {
            withContext(Dispatchers.IO) {
                val resp = napsterService.getTopTracks(artistId)
                resp
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }
}