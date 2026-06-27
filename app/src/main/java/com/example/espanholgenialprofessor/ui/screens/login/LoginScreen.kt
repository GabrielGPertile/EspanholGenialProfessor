package com.example.espanholgenialprofessor.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)
{
    val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }

    androidx.compose.material3.Scaffold(
        snackbarHost = { androidx.compose.material3.SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
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
                    viewModel.login(
                        onSuccess = {
                            navController.navigate(Routes.HOME) {
                                popUpTo(Routes.LOGIN) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                },
                enabled =
                    !viewModel.uiState.isLoading &&
                    viewModel.uiState.email.isNotBlank() &&
                    viewModel.uiState.password.isNotBlank()
            ) {
                Text(
                    if(viewModel.uiState.isLoading) "Entrando..." else "Entrar"
                )
            }

            TextButton(
                onClick = {
                    navController.navigate(Routes.RESET_PASSWORD)
                }
            ) {
                Text("Esqueceu sua senha")
            }

            Button(
                onClick = {
                    navController.navigate(Routes.REGISTER)
                }
            ) {
                Text("Criar conta")
            }
        }
    }
}