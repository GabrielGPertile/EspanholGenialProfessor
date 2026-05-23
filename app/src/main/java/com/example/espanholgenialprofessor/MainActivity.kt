package com.example.espanholgenialprofessor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.espanholgenialprofessor.navigation.NavGraph
import com.example.espanholgenialprofessor.ui.theme.EspanholGenialProfessorTheme
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            EspanholGenialProfessorTheme {

                NavGraph(navController = navController)
            }
        }
    }
}