package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codexorbit.oom_memoryleaks.utils.AppConstants
import com.codexorbit.oom_memoryleaks.utils.AppConstants.BACK

@Composable
fun MemoryLeakScreen(
    onClick: (String) -> Unit,
    onLaunchActivity: (String, Boolean) -> Unit = { _, _ -> }
) {

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top
    ) {
        CommonToolbar("Memory Leak Screen", onBackClick = {onClick(BACK)})

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Context Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_CONTEXT, true) })
            MyButton(text = "Safe Context Passing", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_CONTEXT, false
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "View Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_VIEW, true) })
            MyButton(text = "Safe View Removing", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_VIEW, false
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Handler Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_HANDLER, true) })
            MyButton(text = "Safe Handler Unregistered", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_HANDLER, false
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Coroutine Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_COROUTINE_ACTIVITY, true) })
            MyButton(text = "Safe Coroutine Scope", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_COROUTINE_ACTIVITY, false
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Receiver Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_RECEIVER, true) })
            MyButton(text = "Safe Receiver Unregister", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_RECEIVER, false
                )
            })
        }

        Text("Below In Compose ")

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Leaky Observer",
                onClick = { onClick(AppConstants.LEAKY_OBSERVER) })

            MyButton(text = "Fixed Observer", color = Color.Green, onClick = {
                onClick(
                    AppConstants.FIXED_OBSERVER
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Leaky Coroutine",
                onClick = { onClick(AppConstants.LEAKY_COROUTINE) })

            MyButton(text = "Fixed Coroutine", color = Color.Green, onClick = {
                onClick(
                    AppConstants.FIXED_COROUTINE
                )
            })
        }

        MyButton(text = "Code Sample", color = Color.Cyan, onClick = {
            onClick(
                AppConstants.CODE_SAMPLE
            )
        })

    }


}