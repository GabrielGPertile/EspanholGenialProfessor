package com.example.espanholgenialprofessor.ui.screens.reset_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.navigation.Routes

@Composable
fun ResetPasswordScreen(
    navController: NavHostController,
    viewModel: ResetPasswordViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        viewModel.errorMessage?.let {
            Text(text = it)
        }

        TextField(
            value = viewModel.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("email") }
        )

        Button(
            onClick = {
                viewModel.sendResetPasswordEmail(
                    onSuccess = {
                            navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.RESET_PASSWORD) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            Text("Enviar solicitação")
        }

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Cancelar Solicitação")
        }
    }
}