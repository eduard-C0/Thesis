package com.example.musicstreaming.music.musicstreaming.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicstreaming.commonVO.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase
) : ViewModel() {

    val userInformation: MutableLiveData<User> = MutableLiveData()
    val loadingProgressBar: MutableLiveData<Boolean> = MutableLiveData(true)

    fun getUserInformation() {
        loadingProgressBar.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val user = getUserInformationUseCase.invoke()
            userInformation.postValue(user)
            loadingProgressBar.postValue(false)
        }
    }
}