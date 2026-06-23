package com.example.espanholgenialprofessor.ui.screens.reset_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ResetPasswordViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set

    fun onEmailChange(value: String) {
        email = value
    }
}