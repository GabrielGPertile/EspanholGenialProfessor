package com.example.espanholgenialprofessor.ui.screens.student

import android.R.attr.onClick
import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.espanholgenialprofessor.navigation.Routes
import com.example.espanholgenialprofessor.ui.screens.teacher.HomeTeacherViewModel

@Composable
fun HomeStudentScreen(
    navController: NavController,
    viewModel: HomeTeacherViewModel = hiltViewModel()
)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tela do Aluno")

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