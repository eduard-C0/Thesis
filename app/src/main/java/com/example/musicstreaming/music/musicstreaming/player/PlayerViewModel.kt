package com.example.musicstreaming.music.musicstreaming.player

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicstreaming.music.musicstreaming.MusicManager
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.utils.MediaPlayerCallBack
import java.util.*
import kotlin.collections.ArrayDeque

class PlayerViewModel : ViewModel() {

    private val musicManager = MusicManager
    val currentTrack: MutableLiveData<Track> = MutableLiveData()
    val queueTrackList: MutableLiveData<ArrayDeque<Track>> = MutableLiveData(ArrayDeque())
    val stackTrackList: MutableLiveData<Stack<Track>> = MutableLiveData(Stack())
    val emptyQueue: MutableLiveData<Boolean> = MutableLiveData(true)
    val emptyStack: MutableLiveData<Boolean> = MutableLiveData(true)
    val isPlaying: MutableLiveData<Boolean> = MutableLiveData(false)
    val isCompleted: MutableLiveData<Boolean> = MutableLiveData(false)

    fun play(track: Track) {
        currentTrack.postValue(track)
        isPlaying.postValue(true)
        musicManager.play(track, (object : MediaPlayerCallBack {
            override fun onNotification() {
                isPlaying.postValue(false)
                isCompleted.postValue(true)
                next()
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
                next()
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

    fun stop() {
        musicManager.stop()
        isPlaying.postValue(false)
    }

    fun addToQueue(track: Track) {
        queueTrackList.value?.addLast(track)
        emptyQueue.postValue(false)
        Log.d("PlayerViewModel", "Added Last to queue!")
    }

    fun addFirstToQueue(track: Track) {
        queueTrackList.value?.addFirst(track)
        emptyQueue.postValue(false)
        Log.d("PlayerViewModel", "Added First to queue!")
    }

    fun next() {
        if (queueTrackList.value?.isNotEmpty() == true) {
            queueTrackList.value?.first()?.let {
                currentTrack.value?.let { current -> addToStack(current) }
                play(it)
            }
            queueTrackList.value?.removeFirst()
            queueTrackList.postValue(queueTrackList.value)
            stackTrackList.postValue(stackTrackList.value)
        } else {
            emptyQueue.postValue(true)
            Log.d("PlayerViewModel", "Queue is empty")
        }
    }

    private fun addToStack(track: Track) {
        stackTrackList.value?.push(track)
        emptyStack.postValue(false)
        Log.d("PlayerViewModel", "Added to queue!")
    }

    fun previous() {
        if (stackTrackList.value?.isNotEmpty() == true) {
            stackTrackList.value?.pop()?.let {
                currentTrack.value?.let { current -> addFirstToQueue(current) }
                play(it)
            }
            stackTrackList.postValue(stackTrackList.value)
            queueTrackList.postValue(queueTrackList.value)
        } else {
            emptyStack.postValue(true)
            Log.d("PlayerViewModel", "Queue is empty")
        }
    }

    fun setEmptyQueue() {
        emptyQueue.postValue(true)
    }

    fun setEmptyStack() {
        emptyStack.postValue(true)
    }

    fun addToQueueFavorites(trackList: List<Track>?) {
        if (!trackList.isNullOrEmpty()) {
            queueTrackList.value?.addAll(trackList)
            queueTrackList.postValue(queueTrackList.value)
            emptyQueue.postValue(false)
            Log.d("PlayerViewModel", "Added Favorites to queue!")
            if (currentTrack.value == null) {
                next()
            }
        }
    }

}