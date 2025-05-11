package com.prueba.appmle.ui.theme.utils.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.prueba.appmle.model.Course
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Typography

@Composable
fun CardSearch(
    course: Course,
    navigate: (String) -> Unit
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color7
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {

        }
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .clickable(
                    onClick = {
                        navigate("courseDetail/${course.id}")
                    }
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = course.image,
                contentDescription = "Imagen desde URL",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(16f/9f)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.FillHeight,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.Center),
                            color = Color6,
                            strokeWidth = 2.dp
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.width(8.dp).fillMaxHeight())
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = course.title,
                style = Typography.bodyMedium
            )
        }
    }
}