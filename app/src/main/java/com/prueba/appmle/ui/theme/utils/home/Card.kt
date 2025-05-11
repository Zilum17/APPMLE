package com.prueba.appmle.ui.theme.utils.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.prueba.appmle.model.Course
import com.prueba.appmle.ui.theme.utils.Color1
import com.prueba.appmle.ui.theme.utils.Color3
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Color5
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Typography
import java.util.Locale


@Composable
fun ImageCard(
    course: Course,
    navigate: (String) -> Unit
) {
    Card (
        modifier = Modifier
            .width(280.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.Center),
                            color = Color6,
                            strokeWidth = 2.dp
                        )
                    }
                }
            )
            Text(
                modifier = Modifier.padding(16.dp, 8.dp).fillMaxWidth(),
                text = course.title,
                maxLines = 1,
                color = Color1,
                style = Typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Row (
                modifier = Modifier.fillMaxWidth().padding(16.dp, 4.dp, 16.dp, 16.dp)
            ) {
                Row (modifier = Modifier.fillMaxWidth(.5f), horizontalArrangement = Arrangement.Start){
                    Icon(
                        imageVector = Icons.Outlined.Book,
                        contentDescription = course.level,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = course.level.replaceFirstChar { it.uppercaseChar() },
                        maxLines = 1,
                        color = Color3,
                        style = Typography.bodyLarge.copy(
                            fontSize = 14.sp,
                        )
                    )
                }
                Row (modifier = Modifier.fillMaxWidth(.5f), horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Outlined.StarOutline,
                        contentDescription = "${course.qualification}",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "${course.qualification}",
                        maxLines = 1,
                        color = Color3,
                        style = Typography.bodyLarge.copy(
                            fontSize = 14.sp
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(2.dp).fillMaxWidth().background(Color6))
            Button(
                modifier = Modifier.fillMaxWidth(1f).height(55.dp),
                onClick = {
                    navigate("courseDetail/${course.id}")
                },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color5,
                    disabledContainerColor = Color4,
                    disabledContentColor = Color6
                )
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (course.isFree) "Es gratis" else "$ ${course.price} MXN",
                        style = Typography.bodyMedium.copy(
                            fontSize = 16.sp
                        )
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                        contentDescription = "Ir al curso",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}