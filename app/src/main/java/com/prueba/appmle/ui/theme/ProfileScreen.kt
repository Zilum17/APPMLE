package com.prueba.appmle.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.prueba.appmle.model.Course
import com.prueba.appmle.model.User
import com.prueba.appmle.ui.theme.utils.Color3
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Color5
import com.prueba.appmle.ui.theme.utils.Color6
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Color8
import com.prueba.appmle.ui.theme.utils.Typography
import com.prueba.appmle.viewmodel.AuthViewModel
import com.prueba.appmle.viewmodel.CoursesViewModel

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    coursesViewModel: CoursesViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val user: User? by authViewModel.userData.observeAsState(initial = null)
    val courses: List<Course> by coursesViewModel.courses.observeAsState(initial = emptyList())
    var currentPage = remember { mutableIntStateOf(0) }
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
                    text = "Perfil".uppercase(),
                    style = Typography.titleLarge.copy(
                        letterSpacing = 4.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    color = Color4
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                UserProfile(user = user)
                ControlProfile(currentPage)
                PagesProfile(
                    currentPage,
                    courses,
                    user
                ) {
                    authViewModel.logout(context)
                    navController.navigate("login") {
                        popUpTo("profile") { inclusive = true }
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfile(user: User?) {
    Row (
        modifier = Modifier
            .height(120.dp)
            .zIndex(1f)
            .fillMaxWidth()
            .padding(0.dp, 1.dp, 0.dp, 0.dp)
            .background(Color7),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SubcomposeAsyncImage(
            model = user?.image,
            contentDescription = "Imagen desde URL",
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .padding(24.dp)
                .clip(
                    RoundedCornerShape(45.dp)
                )
                .border(
                    width = 2.dp,
                    color = Color5,
                    shape = RoundedCornerShape(45.dp)
                ),
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
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = (user?.firstName + " " + user?.lastName).uppercase(),
            style = Typography.bodyLarge.copy(
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            color = Color4,
            maxLines = 2
        )
    }
}

@Composable
fun ControlProfile(currentPage: MutableState<Int>) {
    Row (
        modifier = Modifier
            .shadow(elevation = 2.dp)
            .height(70.dp)
            .fillMaxWidth()
            .padding(0.dp, 1.dp, 0.dp, 1.dp)
            .background(Color7),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .clickable( onClick = {
                    currentPage.value = 0
                }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Cursos Completados",
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.bodyLarge.copy(
                    fontSize = 14.sp
                )
            )
        }
        Spacer(modifier = Modifier.width(1.dp).fillMaxHeight())
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()
                .clickable( onClick = {
                    currentPage.value = 1
                }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Informacion",
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.bodyLarge.copy(
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Composable
fun PagesProfile(
    currentPage: MutableState<Int>,
    courses: List<Course>,
    user: User?,
    logout: () -> Unit) {
    when (currentPage.value) {
        0 -> CursesProfile(
            courses = courses
        )
        1 -> DataProfile (
            user = user
        ) {
            logout()
        }
    }
}

@Composable
fun DataProfile(
    user: User?,
    logout: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 4.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color7)
                .padding(8.dp)
        ) {
            Row {
                Text(
                    text = "Datos Personales",
                    modifier = Modifier
                        .padding(8.dp, 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = Typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        color = Color3
                    )
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Nombre/s: " + user?.firstName,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        color = Color3
                    )
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Apellido/s: " + user?.lastName,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        color = Color3
                    )
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Email: " + user?.email,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        color = Color3
                    )
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Fecha de Registro: " + user?.dateRegister,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = Typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        color = Color3
                    )
                )
            }
        }
    }
    Button (
        modifier = Modifier.fillMaxWidth(1f).height(55.dp).padding(50.dp, 0.dp),
        onClick = {
            logout()
        },
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color5,
            disabledContainerColor = Color4,
            disabledContentColor = Color6
        )
    ) {
        Text(
            text = "cerrar sesion".uppercase(),
            style = Typography.bodyMedium
        )
    }
}



@Composable
fun CursesProfile(courses: List<Course>) {
    Spacer(modifier = Modifier.height(10.dp))
    courses.forEach { course ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp, 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color7
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Outlined.CheckCircleOutline,
                        contentDescription = "Ir al curso",
                        modifier = Modifier.size(60.dp).padding(16.dp),
                        tint = Color8
                    )
                    Text(
                        text = course.title,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        style = Typography.bodyLarge

                    )

                }
            }
        }
    }

}
