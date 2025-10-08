package com.codexorbit.oom_memoryleaks.presentation.activities

import android.content.Context
import android.content.Intent
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineLeakActivity : ComponentActivity() {
    var forLeak = false
    private var scope : CoroutineScope? = null
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forLeak = intent?.getBooleanExtra(FOR_LEAK,false) ?: false
        println("For Leak $forLeak")
        if(forLeak){
            GlobalScope.launch {
                delay(10000)
                println("Leak Still running...") // Holds reference to Activity
            }
        }else{
            scope = CoroutineScope(Dispatchers.Main + Job())
            scope?.launch {
                delay(10000)
                println("Safe Cancelled properly")
            }
        }
        setContent {
            OOMMemoryLeaksTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Column(Modifier.Companion.padding(innerPadding).padding(16.dp)) {
                        Text("Coroutine Memory Leak Screen. Memory will ${if (forLeak) "Leak" else "Not Leak"} here")
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if(!forLeak){
            scope?.cancel() // Cancelled coroutine scope here
        }
    }

    companion object{
        const val FOR_LEAK = "for_leak"
        fun getInstance(context: Context, forLeak: Boolean): Intent {
            return Intent(context.applicationContext, CoroutineLeakActivity::class.java).also {
                it.putExtra(FOR_LEAK,forLeak)
            }
        }
    }
}