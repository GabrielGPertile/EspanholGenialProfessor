package com.example.espanholgenialprofessor.ui.screens.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = viewModel()
)
{
    val isReady by viewModel.isReady.collectAsStateWithLifecycle()

    Text("Splash Screen")

    LaunchedEffect(isReady) {
        if(isReady) {

        }
    }
}