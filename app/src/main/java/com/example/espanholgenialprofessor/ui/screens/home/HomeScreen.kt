package com.example.espanholgenialprofessor.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.domain.user.UserProfile
import com.example.espanholgenialprofessor.domain.user.UserRole
import com.example.espanholgenialprofessor.ui.screens.student.HomeStudentScreen
import com.example.espanholgenialprofessor.ui.screens.teacher.HomeTeacherScreen

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavHostController,
)
{
    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    val userProfile = viewModel.userProfile

    if(userProfile == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        return
    }

   when(userProfile.role) {
       UserRole.STUDENT -> {
           HomeStudentScreen(
               navController = navController,
               userProfile = userProfile
           )
       }

       UserRole.TEACHER -> {
           HomeTeacherScreen(
               navController = navController,
               userProfile = userProfile
           )
       }
   }
}