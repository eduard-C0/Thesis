package com.example.musicstreaming.music.authentification.register

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.BackendService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUseCase @Inject constructor(private val backendService: BackendService) {

    suspend operator fun invoke(user: User): Boolean? {
        return try {
            withContext(Dispatchers.IO) {
                val resp = backendService.register(user)
                resp
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }
}