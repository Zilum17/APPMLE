package com.prueba.appmle.ui.theme

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.prueba.appmle.model.Course
import com.prueba.appmle.ui.theme.utils.Color3
import com.prueba.appmle.ui.theme.utils.Color5
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Typography
import com.prueba.appmle.ui.theme.utils.home.ImageCard
import com.prueba.appmle.viewmodel.CoursesViewModel

@Composable
fun HomeScreen(coursesViewModel: CoursesViewModel, navController: NavController) {

    val courses: List<Course> by coursesViewModel.courses.observeAsState(initial = emptyList())
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val navKey by navController.currentBackStackEntryAsState()
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = true
    }
    DisposableEffect(lifecycleOwner, navKey) {
        if (courses.isEmpty())
            coursesViewModel.getCourses(context)
        onDispose {
            Log.d("HomeScreen", "HomeScreen saliendo de la composición, cancelando operaciones")
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
                        .padding(0.dp, 10.dp, 0.dp, 20.dp)
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
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
                    modifier = Modifier.fillMaxWidth().padding(0.dp,16.dp).height(300.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(courses) { course ->
                        ImageCard(course = course)
                    }
                }
                Text(
                    modifier = Modifier
                        .padding(16.dp, 32.dp, 16.dp, 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = "Cursos Recomendados",
                    style = Typography.titleLarge.copy(
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    color = Color3
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(0.dp,16.dp).height(300.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(courses) { course ->
                        ImageCard(course = course)
                    }
                }

            }
        }
    }

}
