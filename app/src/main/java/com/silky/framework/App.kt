package com.silky.framework

import android.app.Application
import com.silky.framework.ui.common.NotificationApp
import org.koin.android.ext.android.inject

class App: Application() {
    private val notificationApp: NotificationApp by inject()

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
        notificationApp.createChannelDownload()
    }
}