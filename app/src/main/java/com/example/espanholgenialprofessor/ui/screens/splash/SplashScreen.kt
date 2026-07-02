package com.example.espanholgenialprofessor.ui.screens.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.navigation.Routes

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = viewModel(),
    navController: NavHostController
)
{
    val destination by viewModel.destination.collectAsStateWithLifecycle()

    LaunchedEffect(destination) {
        when(destination) {
            SplashDestination.LOADING -> Unit

            SplashDestination.LOGIN -> {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }

            SplashDestination.HOME -> {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }
        }
    }
}