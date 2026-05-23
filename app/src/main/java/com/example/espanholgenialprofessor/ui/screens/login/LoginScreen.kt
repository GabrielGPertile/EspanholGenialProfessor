package com.example.espanholgenialprofessor.ui.screens.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavHostController
)
{
    Button(
        onClick = {
            navController.navigate(Routes.HOME) {
                popUpTo(Routes.LOGIN) { inclusive = true }
                launchSingleTop = true
            }
        }
    ) {
        Text("Entrar")
    }
}