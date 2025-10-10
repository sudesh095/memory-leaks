package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.codexorbit.oom_memoryleaks.utils.AppConstants.BACK

object GlobalHolder {
    val observers = mutableListOf<DefaultLifecycleObserver>()
}

@Composable
fun LeakyObserverScreen(onClick:(String) -> Unit){
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val observer = object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                println("Leak OnStart")
            }
        }
        // Leaks: Global reference holds it
        (context as ComponentActivity).lifecycle.addObserver(observer)
        GlobalHolder.observers.add(observer)
        onDispose { }
    }


    Column(Modifier.fillMaxSize()) {
        CommonToolbar("Leaky Observer", onBackClick = {onClick(BACK)})
        Spacer(modifier = Modifier.height(40.dp))
        Text("This one WILL leak.")

    }

}