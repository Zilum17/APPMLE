package com.prueba.appmle

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.prueba.appmle.ui.theme.CoursesScreen
import com.prueba.appmle.ui.theme.HomeScreen
import com.prueba.appmle.ui.theme.LoginScreen
import com.prueba.appmle.ui.theme.ProfileScreen
import com.prueba.appmle.ui.theme.RegisterScreen
import com.prueba.appmle.ui.theme.SearchScreen
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Loading
import com.prueba.appmle.ui.theme.utils.Nav
import com.prueba.appmle.ui.theme.utils.NavItem
import com.prueba.appmle.viewmodel.AuthViewModel
import com.prueba.appmle.viewmodel.CoursesViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Preview
@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val coursesViewModel = CoursesViewModel(authViewModel = authViewModel)
    val isTokenValid by authViewModel.tokenVerificationResult.observeAsState()
    val startDestination = "loading"

    val navItems = listOf(
        NavItem("home", "Inicio", Icons.Outlined.Home),
        NavItem("search", "Buscar",  Icons.Outlined.Search),
        NavItem("courses", "Cursos",  Icons.Outlined.CollectionsBookmark),
        NavItem("profile", "Perfil", Icons.Outlined.Person)
    )
    val showBottomBar = navController.currentBackStackEntryAsState().value?.destination?.route in listOf("home", "search", "courses", "profile")

    LaunchedEffect(isTokenValid) {
        if (isTokenValid != null) {
            val destination = if (isTokenValid == true) "home" else "login"
            navController.navigate(destination) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                launchSingleTop = true
            }
        }
    }
    LaunchedEffect(Unit) {
        if (isTokenValid == null)
            authViewModel.verifyToken(context)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color4
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(
                "loading",
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) {
                Loading()
            }
            composable(
                "login",
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) {
                LoginScreen(authViewModel = authViewModel, navController = navController)
            }
            composable(
                "register",
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) {
                RegisterScreen(authViewModel = authViewModel, navController = navController)
            }
            composable(
                "home",
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) {
                HomeScreen(coursesViewModel = coursesViewModel)
            }
            composable(
                "search",
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) { SearchScreen(navController = navController) }
            composable(
                "courses",
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) { CoursesScreen(navController = navController) }
            composable(
                "profile",
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) { ProfileScreen(authViewModel = authViewModel, navController = navController) }
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            if (showBottomBar) {
                Nav(
                    navController = navController,
                    items = navItems,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}