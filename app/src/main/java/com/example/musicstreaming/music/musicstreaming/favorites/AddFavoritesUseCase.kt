package com.example.musicstreaming.music.musicstreaming.favorites

import com.example.musicstreaming.services.BackendService
import com.example.musicstreaming.services.NapsterService
import com.example.musicstreaming.services.dtos.ResponseMessage
import com.example.musicstreaming.services.dtos.TopTrack
import com.example.musicstreaming.services.dtos.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddFavoritesUseCase @Inject constructor(private val backendService: BackendService) {

    suspend operator fun invoke(track: Track): ResponseMessage? {
        return try {
            withContext(Dispatchers.IO) {
                val resp = backendService.addToFavorites(track)
                resp
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }
}