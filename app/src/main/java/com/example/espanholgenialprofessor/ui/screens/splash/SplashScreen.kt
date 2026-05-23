package com.example.espanholgenialprofessor.ui.screens.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = viewModel(),
    navController: NavHostController
)
{
    val isReady by viewModel.isReady.collectAsStateWithLifecycle()

    LaunchedEffect(isReady) {
        if(isReady) {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }
}