package com.example.espanholgenialprofessor.ui.screens.teacher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.espanholgenialprofessor.navigation.Routes

@Composable
fun HomeTeacherScreen(
    navController: NavController,
    viewModel: HomeTeacherViewModel = hiltViewModel()
)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tela do Professor")

        Button(
            onClick = {
                viewModel.logout()

                navController.navigate(Routes.LOGIN) {
                    popUpTo(0)
                }
            }
        ) {
            Text("Sair")
        }
    }
}