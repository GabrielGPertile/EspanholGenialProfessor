package com.example.espanholgenialprofessor.ui.screens.reset_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    var email by mutableStateOf("")
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onEmailChange(value: String) {
        email = value
    }

    fun sendResetPasswordEmail(onSuccess: () -> Unit) {

        errorMessage = null

        if(email.isBlank()) {
            errorMessage = "Informe um e-mail"

            return
        }

        firebaseAuth.sendPasswordResetEmail(
            email.trim()
        )
            .addOnCompleteListener { task ->

                if(task.isSuccessful) {
                    onSuccess()
                } else {
                    errorMessage = task.exception?.message
                }
            }
    }
}