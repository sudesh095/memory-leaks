package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codexorbit.oom_memoryleaks.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonToolbar(
    title: String,
    canShowBackIcon : Boolean = true,
    onBackClick: () -> Unit
) {
    Row(Modifier.background(Color.Green.copy(alpha = 0.7f)).height(50.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
       if(canShowBackIcon) {
           Image(
               painter = painterResource(id = R.drawable.ic_back_arrow),
               contentDescription = "Back icon",
               modifier = Modifier.size(50.dp).clickable{ onBackClick()}.padding(8.dp),
               colorFilter = ColorFilter.tint(Color.White) // Optional tint
           )
       }
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
            color = Color.White
        )
        if(canShowBackIcon) {
            Box(
                modifier = Modifier.size(50.dp).padding(8.dp)
            )
        }
    }
}
