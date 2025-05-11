package com.prueba.appmle.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun LessonScreen(

) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color6)
    ) {
        Column (
            Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .zIndex(1f)
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(4.dp)
                    .background(Color7),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
//                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            }
        }
    }
}