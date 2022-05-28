package com.example.musicstreaming.music.authentification.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicstreaming.commonVO.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {

    val registerStatus : MutableLiveData<Boolean> = MutableLiveData()

    fun register(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val status = registerUseCase.invoke(user)
            registerStatus.postValue(status)
        }
    }
}