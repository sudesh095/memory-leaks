package com.codexorbit.oom_memoryleaks.presentation.views

import android.annotation.SuppressLint
import android.os.Trace
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codexorbit.oom_memoryleaks.presentation.viewmodels.JankyViewModel
import com.codexorbit.oom_memoryleaks.utils.AppConstants.BACK

@Composable
fun JankyListScreen(onClick:(String)-> Unit, viewModel: JankyViewModel = viewModel()) {
    val list by viewModel.items.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        CommonToolbar("Jenky List Screen", onBackClick = {onClick(BACK)})
        LazyColumn {
            items(list.size) { item ->
                // Heavy computation on main thread during composition
                val reversed = expensiveOperation(list[item])
                Text(reversed, modifier = Modifier.fillMaxWidth().padding(8.dp), textAlign = TextAlign.Center)
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            }
        }
    }


}

@SuppressLint("UnclosedTrace")
fun expensiveOperation(text: String): String {
    Trace.beginSection("expensiveOperation")
    Thread.sleep(10) // Simulate CPU work
    Trace.endSection()
    return text.reversed()
}