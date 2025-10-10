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
fun JenkyScreen(
    onClick: (String) -> Unit
) {

    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top
    ) {
        CommonToolbar("Jenky Demo", onBackClick = {onClick(BACK)})
        MyButton(
            text = "Jenky List Screen", color = Color.Red,
            onClick = { onClick(AppConstants.JENKY_LIST_SCREEN) })

        MyButton(
            text = "Smooth List Screen", color = Color.Green,
            onClick = { onClick(AppConstants.SMOOTH_LIST_SCREEN) })
    }


}