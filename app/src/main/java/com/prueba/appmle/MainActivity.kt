package com.prueba.appmle

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prueba.appmle.ui.theme.HomeScreen
import com.prueba.appmle.ui.theme.LoginScreen
import com.prueba.appmle.ui.theme.RegisterScreen
import com.prueba.appmle.ui.theme.utils.Color4
import com.prueba.appmle.ui.theme.utils.Loading
import com.prueba.appmle.viewmodel.LoginViewModel

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


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color4
    ) {
        NavHost(navController = navController, startDestination = "login") {
            composable(
                "login",
                enterTransition = {
                    slideInVertically(initialOffsetY = { -it }) + fadeIn()
                },
                exitTransition = {
                    slideOutVertically(targetOffsetY = { -it }) + fadeOut()
                },
                popEnterTransition = {
                    slideInVertically(initialOffsetY = { it }) + fadeIn()
                },
                popExitTransition = {
                    slideOutVertically(targetOffsetY = { it }) + fadeOut()
                }
            ) {
                LoginScreen(viewModel = LoginViewModel(), navController = navController)
            }
            composable(
                "register",
                enterTransition = {
                    slideInVertically(initialOffsetY = { it }) + fadeIn()
                },
                exitTransition = {
                    slideOutVertically(targetOffsetY = { it }) + fadeOut()
                },
                popEnterTransition = {
                    slideInVertically(initialOffsetY = { -it }) + fadeIn()
                },
                popExitTransition = {
                    slideOutVertically(targetOffsetY = { -it }) + fadeOut()
                }
            ) {
                RegisterScreen(viewModel = LoginViewModel(), navController = navController)
            }
            composable(
                "home",
                enterTransition = {
                    slideInVertically(initialOffsetY = { -it }) + fadeIn()
                },
                exitTransition = {
                    slideOutVertically(targetOffsetY = { -it }) + fadeOut()
                },
                popEnterTransition = {
                    slideInVertically(initialOffsetY = { it }) + fadeIn()
                },
                popExitTransition = {
                    slideOutVertically(targetOffsetY = { it }) + fadeOut()
                }
            ) {
                HomeScreen(viewModel = LoginViewModel(), navController = navController)
            }
        }
    }
}