package com.prueba.appmle.ui.theme.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ehsanmsz.mszprogressindicator.progressindicator.BallScaleProgressIndicator

@Composable
fun Loading () {
    Box(
        modifier = Modifier.fillMaxSize().background(Color4),
        contentAlignment = Alignment.Center,

    ) {
        BallScaleProgressIndicator(
            color = Color5,
            animationDuration = 1100,
            diameter = 100.dp
        )
    }
}