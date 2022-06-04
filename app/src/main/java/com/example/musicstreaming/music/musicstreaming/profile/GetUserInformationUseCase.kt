package com.example.musicstreaming.music.musicstreaming.profile

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.BackendService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(private val backendService: BackendService) {

    suspend operator fun invoke(): User? {
        return try {
            withContext(Dispatchers.IO) {
                val resp = backendService.getUser()
                resp
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }
}