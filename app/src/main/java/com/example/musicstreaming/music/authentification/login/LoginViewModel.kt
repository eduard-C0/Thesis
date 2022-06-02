package com.example.musicstreaming.music.authentification.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.music.authentification.register.RegisterUseCase
import com.example.musicstreaming.services.dtos.ResponseMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val loginStatus : MutableLiveData<ResponseMessage> = MutableLiveData()
    val loadingProgressBar : MutableLiveData<Boolean> = MutableLiveData(false)

    fun login(user: User) {
        loadingProgressBar.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val status = loginUseCase.invoke(user)
            loginStatus.postValue(status)
            loadingProgressBar.postValue(false)
        }
    }
}