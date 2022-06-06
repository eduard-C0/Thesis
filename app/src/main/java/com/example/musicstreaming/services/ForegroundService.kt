package com.example.musicstreaming.services

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.IBinder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.musicstreaming.R
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.utils.PictureHandler

class ForegroundService : Service() {

    companion object{
        private const val URL_BASE = "https://api.napster.com/imageserver/v2/albums/"
        private const val URL_BASE_ARTIST_IMAGE_SERVER =
            "https://api.napster.com/imageserver/v2/artists/"
        private const val IMAGE_SIZE = 170
        private const val IMAGE_SIZE_ARTIST_W = 150
        private const val IMAGE_SIZE_ARTIST_H = 100
        private const val NOTIFICATION_ID = 1
    }

    private var notification: Notification? = null
    private lateinit var pendingIntentPrevious: PendingIntent
    private lateinit var pendingIntentPlay: PendingIntent
    private lateinit var pendingIntentNext: PendingIntent

    val urlImageSize = "/images/${IMAGE_SIZE}x${IMAGE_SIZE}.jpg"

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }


    fun createNotification(context: Context, track: Track?) {
        createIntentPreviousSong(context)
        createIntentPlaySong(context)
        createIntentNextSong(context)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = Notification.Builder(context, "1")
            .setSmallIcon(R.drawable.ic_music)
            .setContentTitle(track?.name)
            .setColorized(true)
            .setContentText(track?.artistName)
            .setOnlyAlertOnce(true)
            .setShowWhen(false)
            .addAction(R.drawable.ic_previous, "PREVIOUS", pendingIntentPrevious)
            .addAction(R.drawable.ic_play, "PLAY", pendingIntentPlay)
            .addAction(R.drawable.ic_next, "NEXT", pendingIntentNext)
            .setStyle(Notification.MediaStyle()
                .setShowActionsInCompactView(0,1,2))

        val url = "${URL_BASE}${track?.id}$urlImageSize"

        Glide
            .with(context)
            .asBitmap()
            .load(
                GlideUrl(
                    url, LazyHeaders.Builder()
                        .addHeader("apikey", "M2ExNTkyZWUtOWVlOS00NDU0LWJhOTEtYWFmMjI0NGZhNTM5")
                        .build()
                )
            )
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    notification.setLargeIcon(resource)
                    val notificationReady = notification.build()
                    notificationManager.notify(NOTIFICATION_ID, notificationReady)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    //no action
                }
            })
    }

    private fun createIntentPreviousSong(context: Context) {
        val intentPrevious = Intent(context, ForegroundService::class.java)
            .setAction("PREVIOUS")
        pendingIntentPrevious = PendingIntent.getForegroundService(
            context, 0,
            intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun createIntentPlaySong(context: Context) {
        val intentPrevious = Intent(context, ForegroundService::class.java)
            .setAction("PLAY")
        pendingIntentPlay = PendingIntent.getForegroundService(
            context, 0,
            intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun createIntentNextSong(context: Context) {
        val intentPrevious = Intent(context, ForegroundService::class.java)
            .setAction("NEXT")
        pendingIntentNext = PendingIntent.getForegroundService(
            context, 0,
            intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

}