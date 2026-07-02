package com.example.espanholgenialprofessor.ui.screens.reset_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.R
import com.example.espanholgenialprofessor.navigation.Routes

@Composable
fun ResetPasswordScreen(
    navController: NavHostController,
    viewModel: ResetPasswordViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)
{
    val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }

    androidx.compose.material3.Scaffold(
        snackbarHost = { androidx.compose.material3.SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.logo),
                modifier = Modifier.size(200.dp),
                contentDescription = "Logo do aplicativo"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Redefinir senha",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Insira seu e-mail para redefinir a senha",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = viewModel.uiState.email,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("E-mail")  },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Ícone de e-mail"
                    )
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            viewModel.uiState.error?.let {
                Text(text = it)
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.sendResetPasswordEmail(
                        onSuccess = {
                            navController.navigate(Routes.LOGIN) {
                                popUpTo(Routes.RESET_PASSWORD) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                },
                enabled = !viewModel.uiState.isLoading &&
                        viewModel.uiState.email.isNotBlank()
            ) {
                Text(
                    if(viewModel.uiState.isLoading) "Enviando...." else "Enviar"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Voltar ao login")
            }
        }
    }
}