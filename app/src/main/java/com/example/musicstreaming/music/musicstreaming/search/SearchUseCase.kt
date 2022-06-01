package com.example.musicstreaming.music.musicstreaming.search

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.BackendService
import com.example.musicstreaming.services.NapsterService
import com.example.musicstreaming.services.dtos.NapsterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val napsterService: NapsterService) {

    suspend operator fun invoke(trackName: String): NapsterResponse? {
        return try {
            withContext(Dispatchers.IO) {
                val resp = napsterService.searchTracks(trackName)
                resp
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }
}