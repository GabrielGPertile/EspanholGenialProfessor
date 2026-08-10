package com.example.espanholgenialprofessor.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.domain.user.UserRole
import com.example.espanholgenialprofessor.ui.screens.student.HomeStudentScreen
import com.example.espanholgenialprofessor.ui.screens.teacher.HomeTeacherScreen

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavHostController
)
{
    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    val userProfile = viewModel.userProfile

    if(userProfile == null) {
        return
    }

   when(userProfile.role) {
       UserRole.STUDENT -> {
           HomeStudentScreen(
               navController = navController
           )
       }

       UserRole.TEACHER -> {
           HomeTeacherScreen(
               navController = navController
           )
       }
   }
}