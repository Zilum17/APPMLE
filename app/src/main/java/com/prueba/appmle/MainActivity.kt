package com.prueba.appmle

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.prueba.appmle.ui.theme.HomeScreen
import com.prueba.appmle.ui.theme.LoginScreen
import com.prueba.appmle.ui.theme.ProfileScreen
import com.prueba.appmle.ui.theme.RegisterScreen
import com.prueba.appmle.ui.theme.ResourcesScreen
import com.prueba.appmle.ui.theme.SearchScreen
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Color7
import com.prueba.appmle.ui.theme.utils.Loading
import com.prueba.appmle.ui.theme.utils.Nav
import com.prueba.appmle.viewmodel.AuthViewModel
import com.prueba.appmle.viewmodel.CoursesViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
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
    val coursesViewModel: CoursesViewModel = viewModel()
    val isTokenValid by authViewModel.tokenVerificationResult.observeAsState()
    val startDestination = "loading"
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val routesWithBottomBar = listOf("home", "search", "resources", "profile")
    val routesWithCustomBackground = listOf("login", "register")
    val backgroundColor = if (currentRoute in routesWithCustomBackground) Color4 else Color7
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
    Scaffold(
        bottomBar = {
            if (currentRoute in routesWithBottomBar) {
                Nav(
                    navController = navController,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
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
                    HomeScreen(navController = navController, coursesViewModel = coursesViewModel)
                }
                composable(
                    "search",
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None }
                ) {
                    SearchScreen(
//                        navController = navController
                    )
                }
                composable(
                    "resources",
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None }
                ) {
                    ResourcesScreen(navController = navController)
                }
                composable(
                    "profile",
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None }
                ) {
                    ProfileScreen(
                        authViewModel = authViewModel,
                        coursesViewModel = coursesViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}