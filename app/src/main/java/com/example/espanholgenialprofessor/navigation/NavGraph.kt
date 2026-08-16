package com.example.espanholgenialprofessor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.espanholgenialprofessor.ui.screens.home.HomeScreen
import com.example.espanholgenialprofessor.ui.screens.login.LoginScreen
import com.example.espanholgenialprofessor.ui.screens.register.RegisterScreen
import com.example.espanholgenialprofessor.ui.screens.reset_password.ResetPasswordScreen
import com.example.espanholgenialprofessor.ui.screens.splash.SplashScreen
import com.example.espanholgenialprofessor.ui.screens.student.HomeStudentScreen
import com.example.espanholgenialprofessor.ui.screens.teacher.HomeTeacherScreen

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

        composable(Routes.REGISTER){
            RegisterScreen(navController = navController)
        }

        composable(Routes.HOME)
        {
            HomeScreen(navController = navController)
        }

        composable(Routes.RESET_PASSWORD)
        {
            ResetPasswordScreen(navController = navController)
        }

    }
}