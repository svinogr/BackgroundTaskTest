package com.example.backgroundtasktest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.backgroundtasktest.service.StopwatchService
import com.example.backgroundtasktest.ui.theme.BackgroundTaskTestTheme

class MainActivity : ComponentActivity() {
    val stopwatchService = StopwatchService()
    val isBound by mutableStateOf(true)
     val conection = object : ServiceConnection {
         override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
             val binder = p1 as StopwatchService
             isBound = true
         }

         override fun onServiceDisconnected(p0: ComponentName?) {
             isBound = false
         }
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(this,
            arrayOf("android.permission.POST_NOTIFICATIONS","android.permission.USE_EXACT_ALARM"), 0
        )
/*        ActivityCompat.requestPermissions(this,
            arrayOf(
                "android.permission.FOREGROUND_SERVICE_SYSTEM_EXEMPTED"), 0
        )*/
        setContent {
            BackgroundTaskTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isBound) {
                        Greeting(this)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(context: Context) {
    Button(onClick = {
        val intent = Intent(context, StopwatchService::class.java).also {
            it.action = StopwatchService.Actions.START.toString()
            context.startService(it)
        }

    }) {

    }

}
/*

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val stopwatchService = StopwatchService()
    BackgroundTaskTestTheme {
        Greeting()
    }
}*/
