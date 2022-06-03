package com.example.musicstreaming.music.musicstreaming.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicstreaming.music.musicstreaming.MusicManager
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.utils.MediaPlayerCallBack

class PlayerViewModel : ViewModel() {

    private val musicManager = MusicManager
    val currentTrack: MutableLiveData<Track> = MutableLiveData()
    val isPlaying: MutableLiveData<Boolean> = MutableLiveData(false)
    val isCompleted: MutableLiveData<Boolean> = MutableLiveData(false)

    fun play(track: Track) {
        currentTrack.postValue(track)
        isPlaying.postValue(true)
        musicManager.play(track, (object : MediaPlayerCallBack {
            override fun onNotification() {
                isPlaying.postValue(false)
                isCompleted.postValue(true)
            }
        }))
    }

    fun pause() {
        musicManager.pause()
        isPlaying.postValue(false)
    }

    fun setFinishListener() {
        musicManager.setOnCompletionListener(object : MediaPlayerCallBack {
            override fun onNotification() {
                isPlaying.postValue(false)
                isCompleted.postValue(true)
            }
        })
    }

    fun start() {
        musicManager.start()
        isPlaying.postValue(true)
    }

    fun isPlaying() {
        isPlaying.postValue(musicManager.isPlaying())
    }

    fun stop(){
        musicManager.stop()
        isPlaying.postValue(false)
    }

}