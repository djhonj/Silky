package com.silky.framework.ui.common

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.NotificationCompat
import com.silky.domain.VideoYT
import com.silky.framework.R
import com.silky.framework.ui.downloadlist.DownloadListActivity

class DownloadService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return  null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.extras?.getBoolean("stop")?.let {
            if (it) {
                stopForeground(true)
                stopSelf(startId)

                return START_STICKY
            }
        }

        val video = intent?.extras?.getSerializable("video") as VideoYT
        video?.let {
            val notification: Notification = NotificationApp(applicationContext)
                .prepare(it.Title ?: "notification", "Descargando...", null, null)
                .build()

            startForeground(it.Id!!, notification)
        }

        return START_STICKY
    }
}