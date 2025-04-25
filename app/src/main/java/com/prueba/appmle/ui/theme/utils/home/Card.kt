package com.prueba.appmle.ui.theme.utils.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.prueba.appmle.model.Course
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7


@Composable
fun VideoCard(course: Course) {
    Card (
        modifier = Modifier
            .width(240.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color7
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column {
            SubcomposeAsyncImage(
                model = course.image,
                contentDescription = "Imagen desde URL",
                modifier = Modifier.fillMaxWidth().aspectRatio(16f/9f),
                contentScale = ContentScale.FillWidth,
                loading = {
                    CircularProgressIndicator(modifier = Modifier
                        .align(Alignment.Center)
                        .size(40.dp),
                        color = Color6
                    )
                }
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = course.title,
                maxLines = 1
            )
        }
    }
}