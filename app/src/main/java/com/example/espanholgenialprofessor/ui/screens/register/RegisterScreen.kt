package com.example.espanholgenialprofessor.ui.screens.register

import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.navigation.Routes

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = viewModel.uiState.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("email") }
        )

        TextField(
            value = viewModel.uiState.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Senha") }
        )

        viewModel.uiState.error?.let {
            Text(text = it)
        }

        Button(
            onClick = {
                viewModel.register(
                    onSuccess = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            },
            enabled = !viewModel.uiState.isLoading
        ) {
            Text(
                if(viewModel.uiState.isLoading) "Criando conta..." else "Criar Conta"
            )
        }
    }
}