package com.codexorbit.oom_memoryleaks.presentation.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.codexorbit.oom_memoryleaks.utils.ContextLeak.textView
import com.codexorbit.oom_memoryleaks.ui.theme.OOMMemoryLeaksTheme

class ReceiverLeakActivity : ComponentActivity() {
    var forLeak = false
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            println("Leak Broadcast received")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forLeak = intent?.getBooleanExtra(FOR_LEAK,false) ?: false
        println("For Leak $forLeak")
        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        setContent {
            OOMMemoryLeaksTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Column(Modifier.Companion.padding(innerPadding).padding(16.dp)) {
                        Text("Broadcast Receiver Memory Leak Screen. Memory will ${if (forLeak) "Leak" else "Not Leak"} here")
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if(!forLeak){
            unregisterReceiver(receiver) // Unregistered receiver here so memory will not leak
        }
    }

    companion object{
        const val FOR_LEAK = "for_leak"
        fun getInstance(context: Context, forLeak: Boolean): Intent {
            return Intent(context.applicationContext, ReceiverLeakActivity::class.java).also {
                it.putExtra(FOR_LEAK,forLeak)
            }
        }
    }
}