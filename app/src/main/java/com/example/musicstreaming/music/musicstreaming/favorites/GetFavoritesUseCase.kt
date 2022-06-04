package com.example.musicstreaming.music.musicstreaming.favorites

import com.example.musicstreaming.services.BackendService
import com.example.musicstreaming.services.dtos.ResponseMessage
import com.example.musicstreaming.services.dtos.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val backendService: BackendService) {

    suspend operator fun invoke(): List<Track>? {
        return try {
            withContext(Dispatchers.IO) {
                val resp = backendService.getAllFromFavorites()
                resp
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }
}