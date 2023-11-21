package com.example.backgroundtasktest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class MyApplication : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val chanel = NotificationChannel(
            "stopwatch",
            "stop watch",
            NotificationManager.IMPORTANCE_HIGH
        )

        val natManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        natManager.createNotificationChannel(chanel)

    }



}