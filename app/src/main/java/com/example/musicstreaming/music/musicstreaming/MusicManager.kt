package com.example.musicstreaming.music.musicstreaming

import android.media.AudioAttributes
import android.media.MediaPlayer
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.utils.MediaPlayerCallBack

object MusicManager {

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    fun play(track: Track, mediaPlayerCallBack: MediaPlayerCallBack) {

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }

        mediaPlayer.setDataSource(track.previewURL)
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener { mediaPlayer.start() }

        mediaPlayer.setOnCompletionListener {
            stop()
            mediaPlayerCallBack.onNotification()
        }
    }

    fun start() {
        mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun stop() {
        mediaPlayer.release()
    }

    fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    fun setOnCompletionListener(mediaPlayerCallBack: MediaPlayerCallBack) {
        mediaPlayer.setOnCompletionListener {
            stop()
            mediaPlayerCallBack.onNotification()
        }
    }
}