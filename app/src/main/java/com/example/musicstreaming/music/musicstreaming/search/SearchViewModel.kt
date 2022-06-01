package com.example.musicstreaming.music.musicstreaming.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.music.authentification.register.RegisterUseCase
import com.example.musicstreaming.services.dtos.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    val tracksList : MutableLiveData<List<Track>> = MutableLiveData(emptyList())
    val loadingProgressBar : MutableLiveData<Boolean> = MutableLiveData(false)

    fun search(trackName: String) {
        loadingProgressBar.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val result = searchUseCase.invoke(trackName)
            tracksList.postValue(result?.searchTrackResult?.data?.tracks)
            loadingProgressBar.postValue(false)
        }
    }
}