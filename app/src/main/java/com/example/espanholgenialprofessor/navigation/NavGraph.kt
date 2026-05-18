package com.example.espanholgenialprofessor.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            Text("Splash Screen")
        }

        composable("login") {
            Text("Login Screen")
        }
    }
}