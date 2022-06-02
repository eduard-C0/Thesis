package com.example.musicstreaming.music.authentification.login

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.BackendService
import com.example.musicstreaming.services.dtos.ResponseMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val backendService: BackendService) {

    suspend operator fun invoke(user: User): ResponseMessage? {
        return try {
            withContext(Dispatchers.IO) {
                val resp = backendService.login(user)
                resp
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }
}