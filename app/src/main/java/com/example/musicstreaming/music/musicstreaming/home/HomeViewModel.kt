package com.example.musicstreaming.music.musicstreaming.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicstreaming.music.musicstreaming.search.SearchUseCase
import com.example.musicstreaming.services.dtos.Artist
import com.example.musicstreaming.services.dtos.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val topArtistUseCase: TopArtistUseCase, private val topTrackUseCase: TopTrackUseCase) : ViewModel() {

    val topArtists: MutableLiveData<List<Artist>> = MutableLiveData(emptyList())

    val loadingProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getTopArtists() {
        loadingProgressBar.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val artistResult = topArtistUseCase.invoke()
//            delay(1000)
            artistResult?.artists?.forEach {
                val trackResult = topTrackUseCase.invoke(it.id)
                it.topTracks = trackResult?.tracks
            }
            topArtists.postValue(artistResult?.artists)
            loadingProgressBar.postValue(false)
        }
    }
}