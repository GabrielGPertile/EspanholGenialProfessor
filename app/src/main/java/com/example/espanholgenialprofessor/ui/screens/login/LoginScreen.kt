package com.example.espanholgenialprofessor.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import com.example.espanholgenialprofessor.R
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.navigation.Routes
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

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

        val passwordVisible = remember { mutableStateOf(false) }

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
                text = "Entrar",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Faça login para continuar",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = viewModel.uiState.email,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("E-mail") },

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

            OutlinedTextField(
                value = viewModel.uiState.password,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Senha") },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Ícone de senha"
                    )
                },

                visualTransformation = if (passwordVisible.value)
                    androidx.compose.ui.text.input.VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisible.value = !passwordVisible.value
                        }
                    ) {
                        Icon(
                            imageVector = if (passwordVisible.value)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            viewModel.uiState.error?.let {
                Text(text = it)
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
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

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Routes.RESET_PASSWORD)
                }
            ) {
                Text("Esqueceu sua senha")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Routes.REGISTER)
                }
            ) {
                Text("Criar conta")
            }
        }
    }
}