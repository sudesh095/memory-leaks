package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codexorbit.oom_memoryleaks.presentation.viewmodels.SmoothViewModel
import com.codexorbit.oom_memoryleaks.utils.AppConstants.BACK

@Composable
fun SmoothListScreen(onClick:(String)-> Unit, viewModel: SmoothViewModel = viewModel()) {
    val processed by viewModel.processedItems.collectAsState()
    val derived by remember { derivedStateOf { processed } }

    Column(modifier = Modifier.fillMaxSize()) {
        CommonToolbar("Smooth List Screen", onBackClick = {onClick(BACK)})
        LazyColumn {
            items(derived) { item ->
                Text(item, modifier = Modifier.fillMaxWidth().padding(8.dp), textAlign = TextAlign.Center)
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            }
        }
    }


}