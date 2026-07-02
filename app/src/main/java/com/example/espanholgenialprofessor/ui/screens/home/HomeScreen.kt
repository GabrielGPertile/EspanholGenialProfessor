package com.example.espanholgenialprofessor.ui.screens.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.navigation.Routes
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(
    navController: NavHostController
)
{
    Text("Home Screen")

    Button(
        onClick = {
            FirebaseAuth.getInstance().signOut()

            navController.navigate(Routes.LOGIN) {
                popUpTo(0)
            }
        }
    ) {
        Text("Sair")
    }
}