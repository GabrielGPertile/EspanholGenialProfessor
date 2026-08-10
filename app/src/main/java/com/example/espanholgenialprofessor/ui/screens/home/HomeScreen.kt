package com.example.espanholgenialprofessor.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.domain.user.UserProfile
import com.example.espanholgenialprofessor.domain.user.UserRole
import com.example.espanholgenialprofessor.ui.screens.student.HomeStudentScreen
import com.example.espanholgenialprofessor.ui.screens.teacher.HomeTeacherScreen

@Composable
fun HomeScreen(
    userProfile: UserProfile,
    navController: NavHostController
)
{
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