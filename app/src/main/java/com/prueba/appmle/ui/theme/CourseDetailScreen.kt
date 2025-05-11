package com.prueba.appmle.ui.theme

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Class
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.Subtitles
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.prueba.appmle.model.Lesson
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Color5
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Typography
import com.prueba.appmle.viewmodel.CoursesViewModel

@Composable
fun CourseDetailScreen(
    courseId: String = "",
    coursesViewModel: CoursesViewModel,
    navController: NavController
) {
    val lessons: List<Lesson> by coursesViewModel.lessons.observeAsState(initial = emptyList())
    val context = LocalContext.current
    val course = coursesViewModel.getCourseById(courseId)
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        if (lessons.isEmpty())
            coursesViewModel.getLessons(courseId, context)
    }
    DisposableEffect(lifecycleOwner) {
        onDispose {
            Log.d("DetailScreen", "Saliendo de la composición, cancelando operaciones")
            coursesViewModel.clearLessons()
        }
    }
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color6)
    ) {
        Column (
            Modifier
                .fillMaxSize()
        ) {
            Row (
                modifier = Modifier
                    .zIndex(1f)
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(4.dp)
                    .background(Color7),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton (
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon (
                        Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            }
            Column (
                modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
            ) {
                ImageDetail(
                    courseURL = course?.image.toString()
                )
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth())
                Detail(
                    courseTitle = course?.title.toString(),
                    courseDescription = course?.description.toString(),
                    courseCategory = course?.category.toString(),
                    courseLanguage = course?.language.toString(),
                    courseLevel = course?.level.toString(),
                    courseStar = course?.qualification.toString(),
                    courseDuration = course?.duration.toString()
                )
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth())
                DateDetail(
                    date = course?.dateCreated.toString(),
                    update = course?.dateUpdated.toString()
                )
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth())
                LessonsPreview(
                    lessons = lessons
                )
            }
            Row (
                modifier = Modifier
                    .zIndex(1f)
                    .fillMaxWidth()
                    .shadow(6.dp)
                    .background(Color7),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(77.dp)
                        .padding(vertical = 16.dp, horizontal = 24.dp),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        navController.navigate("lesson/${1}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color5
                    )
                ) {
                    Text(
                        text = "comprar".uppercase(),
                        style = Typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ImageDetail(
    courseURL: String
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            model = courseURL,
            contentDescription = "Imagen desde URL",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f/9f),
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
                            .size(60.dp)
                            .align(Alignment.Center),
                        color = Color6,
                        strokeWidth = 2.dp
                    )
                }
            }
        )
    }
}

@Composable
fun Detail(
    courseTitle: String,
    courseDescription: String,
    courseCategory: String,
    courseLanguage: String,
    courseLevel: String,
    courseStar: String,
    courseDuration: String
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color7),
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(8.dp).fillMaxWidth())
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = courseTitle,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.titleLarge.copy(
                        fontSize = 20.sp,
                        letterSpacing = .5.sp,
                        color = Color5,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color6))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 0.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "Objetivo",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.titleMedium.copy(
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = Color4,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = courseDescription,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodyLarge.copy(
                        lineHeight = 18.sp,
                    )
                )
            }
            Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color6))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 2.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Class,
                    contentDescription = "Buscar",
                    tint = Color4,
                    modifier = Modifier
                        .height(16.dp)
                        .padding(start = 8.dp)
                )
                Text(
                    text = courseCategory,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodySmall,
                    maxLines = 1,
                    color = Color4
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Subtitles,
                    contentDescription = "Buscar",
                    tint = Color4,
                    modifier = Modifier
                        .height(16.dp)
                        .padding(start = 8.dp)
                )
                Text(
                    text = courseLanguage,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodySmall,
                    maxLines = 1,
                    color = Color4
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.School,
                    contentDescription = "Buscar",
                    tint = Color4,
                    modifier = Modifier
                        .height(16.dp)
                        .padding(start = 8.dp)
                )
                Text(
                    text = courseLevel.capitalizeFirstLetter(),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodySmall,
                    maxLines = 1,
                    color = Color4
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = "Buscar",
                    tint = Color4,
                    modifier = Modifier
                        .height(16.dp)
                        .padding(start = 8.dp)
                )
                Text(
                    text = courseStar,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodySmall,
                    maxLines = 1,
                    color = Color4
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.WatchLater,
                    contentDescription = "Buscar",
                    tint = Color4,
                    modifier = Modifier
                        .height(16.dp)
                        .padding(start = 8.dp)
                )
                Text(
                    text = "$courseDuration Hora/s",
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodySmall,
                    maxLines = 1,
                    color = Color4
                )
            }
        }
    }
}

@Composable
fun DateDetail(
    date: String,
    update: String
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color7)
    ) {
        Row (
            modifier = Modifier
                .padding(top = 12.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = "Fecha Creación: $date",
                style = Typography.bodySmall,
                color = Color4
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = "Fecha Actualización: $update",
                style = Typography.bodySmall,
                color = Color4
            )
        }
    }
}

@Composable
fun LessonsPreview(
    lessons: List<Lesson>
) {
    Row (
        modifier = Modifier
        .fillMaxWidth()
        .background(Color7)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            text = "Lecciones",
            textAlign = TextAlign.Center,
            style = Typography.titleMedium
        )
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            lessons.forEach {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .height(48.dp)
                        .background(Color7),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(4.dp)
                            .background(Color5)
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        text = it.title
                    )
                }
                Spacer(modifier = Modifier.height(8.dp).fillMaxWidth())
            }
        }
    }
}

fun String.capitalizeFirstLetter(): String {
    return if (isNotEmpty()) {
        this[0].uppercaseChar() + substring(1)
    } else {
        this
    }
}