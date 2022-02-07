package com.silky.framework.ui.common

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.silky.framework.App
import com.silky.framework.R
import okhttp3.internal.notify
import org.koin.core.context.GlobalContext.get
import org.koin.java.KoinJavaComponent.inject

class NotificationApp(private val context: Context) {
//    lateinit var title: String
//    lateinit var description: String
//    lateinit var pendingIntent: PendingIntent
//    var icon: Int? = null

    private fun createNotificationChannel(channelId:String, name: String) {
        //android 8 o versiones posteriores
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //val name = "Service notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(channelId, name, importance)

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun createChannelDownload() {
        createNotificationChannel("channel_notification", "Notification service")
    }

    fun prepare(title: String, description: String, pendingIntent: PendingIntent?, icon: Int?) : NotificationCompat.Builder {
        var iconDrawable: Int = icon ?: R.mipmap.ic_launcher_round

        //builder
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, "channel_notification")
            .setSmallIcon(iconDrawable)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)

        val largeIcon :Bitmap? = BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.ic_launcher_round)

        if (largeIcon != null) {
            builder.setLargeIcon(largeIcon)
        }

        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent) //redirecciona cuando del click a notification
        }

        return builder
    }

    fun notify(notificationId: Int, notification: NotificationCompat.Builder) {
        //val notification: Notification = builder.build()
        NotificationManagerCompat.from(context).notify(notificationId, notification.build())
    }
}