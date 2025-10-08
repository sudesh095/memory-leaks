package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LeakyCoroutineScreen(onClick:() -> Unit){
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {
        Button(onClick = {
            scope.launch {
                //Long running Job
                delay(60_000)
                println("Still Running Even if screen gone.")
            }
        }) {
            Text("Start Leak Job")
        }

        MyButton(text = "GO Back", color = Color.Blue) {
            onClick()
        }
    }


}