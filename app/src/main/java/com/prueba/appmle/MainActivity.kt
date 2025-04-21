package com.prueba.appmle

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val context = LocalContext.current
    val navController = rememberNavController()
    val viewModel: LoginViewModel = viewModel()
    val isTokenValid by viewModel.tokenVerificationResult.observeAsState()
    val startDestination = "loading"

    LaunchedEffect(isTokenValid) {
        println("MyApp: isTokenValid changed to $isTokenValid")
        if (isTokenValid != null) {
            val destination = if (isTokenValid == true) "home" else "login"
            println("MyApp: Navigating to $destination")
            navController.navigate(destination) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                launchSingleTop = true
            }
        } else {
            println("MyApp: Waiting for token verification")
        }
    }

    LaunchedEffect(Unit) {
        if (isTokenValid == null)
            viewModel.verifyToken(context)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color4
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(
                "loading",
                enterTransition = {
                    scaleIn(initialScale = 0.8f) + fadeIn()
                },
                exitTransition = {
                    scaleOut(targetScale = 0.8f) + fadeOut()
                },
                popEnterTransition = {
                    scaleIn(initialScale = 1.1f) + fadeIn()
                },
                popExitTransition = {
                    scaleOut(targetScale = 1.1f) + fadeOut()
                }
            ) {
                Loading()
            }
            composable(
                "login",
                enterTransition = {
                    scaleIn(initialScale = 0.8f) + fadeIn()
                },
                exitTransition = {
                    scaleOut(targetScale = 0.8f) + fadeOut()
                },
                popEnterTransition = {
                    scaleIn(initialScale = 1.1f) + fadeIn()
                },
                popExitTransition = {
                    scaleOut(targetScale = 1.1f) + fadeOut()
                }
            ) {
                LoginScreen(viewModel = LoginViewModel(), navController = navController)
            }
            composable(
                "register",
                enterTransition = {
                    scaleIn(initialScale = 0.8f) + fadeIn()
                },
                exitTransition = {
                    scaleOut(targetScale = 0.8f) + fadeOut()
                },
                popEnterTransition = {
                    scaleIn(initialScale = 1.1f) + fadeIn()
                },
                popExitTransition = {
                    scaleOut(targetScale = 1.1f) + fadeOut()
                }
            ) {
                RegisterScreen(viewModel = LoginViewModel(), navController = navController)
            }
            composable(
                "home",
                enterTransition = {
                    scaleIn(initialScale = 0.8f) + fadeIn()
                },
                exitTransition = {
                    scaleOut(targetScale = 0.8f) + fadeOut()
                },
                popEnterTransition = {
                    scaleIn(initialScale = 1.1f) + fadeIn()
                },
                popExitTransition = {
                    scaleOut(targetScale = 1.1f) + fadeOut()
                }
            ) {
                HomeScreen()
            }
        }
    }
}