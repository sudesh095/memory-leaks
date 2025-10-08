package com.codexorbit.oom_memoryleaks.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.codexorbit.oom_memoryleaks.utils.ContextLeak
import com.codexorbit.oom_memoryleaks.ui.theme.OOMMemoryLeaksTheme

class ContextLeakActivity : ComponentActivity() {
    var forLeak = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forLeak = intent?.getBooleanExtra(FOR_LEAK,false) ?: false
        println("For Leak $forLeak")
        setContent {
            OOMMemoryLeaksTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Column(Modifier.Companion.padding(innerPadding).padding(16.dp)) {
                        Text("Context Memory Leak Screen. Memory will ${if (forLeak) "Leak" else "Not Leak"} here")
                    }
                }
            }
        }


        if(forLeak) {
            ContextLeak.context = this // This will Leak Activity
        }else {
        ContextLeak.context = applicationContext // This is Safe Always use application context
        }
    }

    companion object{
        const val FOR_LEAK = "for_leak"
        fun getInstance(context: Context, forLeak: Boolean): Intent {
            return Intent(context.applicationContext, ContextLeakActivity::class.java).also {
                it.putExtra(FOR_LEAK,forLeak)
            }
        }
    }
}