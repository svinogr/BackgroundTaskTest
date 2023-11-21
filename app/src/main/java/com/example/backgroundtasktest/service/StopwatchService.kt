package com.example.backgroundtasktest.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.backgroundtasktest.R


class StopwatchService : Service() {
    private var binder = StopWatchBinder()

    inner class StopWatchBinder : Binder() {
        fun getService(): StopwatchService = this@StopwatchService
    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("onStartCommand", "onStartCommand")
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.FINISH.toString() -> stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun start() {
        val notification =
            Notification.Builder(this, "stopwatch").setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Stopwatch").setContentText("00 0 0").build()
        startForeground(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_SYSTEM_EXEMPTED)
    }


    enum class Actions() {
        START, FINISH
    }
}