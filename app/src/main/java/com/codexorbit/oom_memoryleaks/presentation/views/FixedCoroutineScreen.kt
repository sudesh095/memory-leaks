package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codexorbit.oom_memoryleaks.utils.AppConstants.BACK
import kotlinx.coroutines.delay

@Composable
fun FixedCoroutineScreen(onClick:(String) -> Unit){
    var start by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {
        CommonToolbar("Fixed Coroutine", onBackClick = {
            start = false
            onClick(BACK)
        })
        Spacer(modifier = Modifier.height(40.dp))

        Text("Fixed screen with LifecycleObserver")

        MyButton(text = "Start Safe Job", color = Color.Blue) {
            start = true
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