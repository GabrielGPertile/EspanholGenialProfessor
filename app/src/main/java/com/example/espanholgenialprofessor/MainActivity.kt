package com.example.espanholgenialprofessor

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.espanholgenialprofessor.navigation.NavGraph
import com.example.espanholgenialprofessor.ui.theme.EspanholGenialProfessorTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EspanholGenialProfessorTheme {
                NavGraph()
            }
        }
    }
}