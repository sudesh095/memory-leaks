package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.codexorbit.oom_memoryleaks.utils.AppConstants.BACK

@Composable
fun FixedObserverScreen(onClick:(String) -> Unit){
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(Unit) {

        val observer = object : DefaultLifecycleObserver{
            override fun onStart(owner: LifecycleOwner) {
                println("Fixed OnStart")
            }
        }

        //Added Observer and Remove Later
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            //Proper Clean will be removed when screen go away
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(Modifier.fillMaxSize()) {

        CommonToolbar("Fixed Observer", onBackClick = {onClick(BACK)})
        Spacer(modifier = Modifier.height(40.dp))

        Text("Fixed screen with LifecycleObserver")
    }

}