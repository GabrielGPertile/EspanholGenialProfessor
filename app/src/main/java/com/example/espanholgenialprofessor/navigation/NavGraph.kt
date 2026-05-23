package com.example.espanholgenialprofessor.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.espanholgenialprofessor.ui.screens.home.HomeScreen
import com.example.espanholgenialprofessor.ui.screens.login.LoginScreen
import com.example.espanholgenialprofessor.ui.screens.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(Routes.HOME)
        {
            HomeScreen()
        }
    }
}