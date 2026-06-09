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
            value = viewModel.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("email") }
        )

        TextField(
            value = viewModel.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Senha") }
        )

        Button(
            onClick = {
                // depois vamos chamar Firebase aqui
            }
        ) {
            Text("Criar conta")
        }
    }
}