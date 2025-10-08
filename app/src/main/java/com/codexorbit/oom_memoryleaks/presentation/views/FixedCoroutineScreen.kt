package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun FixedCoroutineScreen(onClick:() -> Unit){
    var start by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {
        Text("Fixed screen with LifecycleObserver")

        MyButton(text = "Start Safe Job", color = Color.Blue) {
            start = true
        }

        MyButton(text = "GO Back", color = Color.Blue) {
            start = false
            onClick()
        }
    }

    if(start){
        LaunchedEffect(Unit) {
            //Automatically cancelled when composable Disappear
            delay(60_000)
            println("Fixed Job done safely")
        }
    }

}