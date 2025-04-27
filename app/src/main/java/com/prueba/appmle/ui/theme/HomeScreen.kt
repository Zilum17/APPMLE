package com.prueba.appmle.ui.theme

import android.util.Log
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
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prueba.appmle.model.Course
import com.prueba.appmle.ui.theme.utils.Color3
import com.prueba.appmle.ui.theme.utils.Color5
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Typography
import com.prueba.appmle.ui.theme.utils.home.ImageCard
import com.prueba.appmle.viewmodel.CoursesViewModel

@Composable
fun HomeScreen(coursesViewModel: CoursesViewModel) {
    val courses: List<Course> by coursesViewModel.courses.observeAsState(initial = emptyList())
//    val courses: List<Course> = listOf(
//        Course(
//            id = 1,
//            title = "Gestión Empresarial Efectiva",
//            description = "Aprende estrategias clave para administrar una empresa, desde finanzas hasta liderazgo de equipos",
//            category = "Administración de Empresas",
//            level = "intermedio",
//            image = "http://192.168.0.16:8000/image1.jpg",
//            price = 119.99,
//            isFree = false,
//            subscription = false,
//            duration = 30,
//            qualification = 4.5,
//            reviews = 100,
//            dateCreated = "2023-01-01",
//            dateUpdated = "2023-01-01",
//            status = "publicado"
//        ),
//        Course(
//            id = 2,
//            title = "Marketing Digital para Pymes",
//            description = "Domina las herramientas esenciales para promocionar tu negocio en redes sociales y Google.",
//            category = "Marketing",
//            level = "principiante",
//            image = "http://192.168.0.16:8000/image2.jpg",
//            price = 0.00,
//            isFree = true,
//            subscription = false,
//            duration = 0,
//            qualification = 4.7,
//            reviews = 10,
//            dateCreated = "2023-01-01",
//            dateUpdated = "2023-01-01",
//            status = "publicado"
//        )
//    )
    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        if (courses.isEmpty())
            coursesViewModel.getCourses(context)

    }
    Log.d("CoursesScreen", "Cursos en Composable: $courses")
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
                    ImageCard(course = course)
                }
            }
        }
    }
}
