package com.prueba.appmle.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prueba.appmle.model.Course
import com.prueba.appmle.ui.theme.utils.Color3
import com.prueba.appmle.ui.theme.utils.Color5
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Typography
import com.prueba.appmle.ui.theme.utils.home.VideoCard

@Preview(showSystemUi = true)
@Composable
fun HomeScreen() {
//    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
    val courses: List<Course> = listOf(
        Course(
            id = 1,
            title = "Video 1",
            description = "Descripción del video 1",
            category = "Categoría 1",
            level = "Nivel 1",
            image = "http://192.168.0.16:8000/image1.jpg",
            price = 19.99,
            isFree = true,
            subscription = false,
            duration = 0,
            qualification = 4.5,
            reviews = 100,
            dateCreated = "2023-01-01",
            dateUpdated = "2023-01-01",
            status = "Publicado"
        ),
        Course(
            id = 1,
            title = "Video 2",
            description = "Descripción del video 1",
            category = "Categoría 1",
            level = "Nivel 1",
            image = "http://192.168.0.16:8000/image2.jpg",
            price = 19.99,
            isFree = true,
            subscription = false,
            duration = 0,
            qualification = 4.5,
            reviews = 100,
            dateCreated = "2023-01-01",
            dateUpdated = "2023-01-01",
            status = "Publicado"
        )
    )
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color6)
    ) {
        Column (
            Modifier
                .fillMaxSize()
        ){
            Row (
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp
                    )
                    .fillMaxWidth()
                    .background(Color7)
            ){
                Text(
                    modifier = Modifier
                        .padding(0.dp, 56.dp, 0.dp, 20.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "EmprendiAPP".uppercase(),
                    style = Typography.titleLarge.copy(
                        letterSpacing = 4.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    color = Color5
                )
            }
            Text(
                modifier = Modifier
                    .padding(16.dp, 32.dp, 16.dp, 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = "Nuevos Cursos",
                style = Typography.titleLarge.copy(
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
                color = Color3
            )
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(courses) { course ->
                    VideoCard(course = course)
                }
            }
//            Row (
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
////                VideoPlayer("http://192.168.0.16:8000/video1.mp4")
//            }
        }
    }
}

//            Button(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp, 16.dp, 16.dp, 0.dp),
//                onClick = {
//                    viewModel.logout(context)
//                    navController.navigate("login") {
//                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
//                    }
//                }
//            ) {
//
//            }