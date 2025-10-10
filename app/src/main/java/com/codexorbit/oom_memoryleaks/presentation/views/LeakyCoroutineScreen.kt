package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codexorbit.oom_memoryleaks.utils.AppConstants.BACK
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LeakyCoroutineScreen(onClick:(String) -> Unit){
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {

        CommonToolbar("Leaky Coroutine", onBackClick = {onClick(BACK)})
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {
            scope.launch {
                //Long running Job
                delay(60_000)
                println("Still Running Even if screen gone.")
            }
        }) {
            Text("Start Leak Job")
        }

    }


}