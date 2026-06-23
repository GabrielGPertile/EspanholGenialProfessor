package com.example.espanholgenialprofessor.ui.screens.reset_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    var uiState by mutableStateOf(ResetPasswordUiState())
        private set

    fun onEmailChange(value: String) {
        uiState = uiState.copy(email = value)
    }

    fun sendResetPasswordEmail(onSuccess: () -> Unit) {
        if(uiState.email.isBlank()) {
            uiState = uiState.copy(
                error = "Informe um e-mail"
            )
            return
        }

        uiState = uiState.copy(
            isLoading = true,
            error = null
        )


        firebaseAuth.sendPasswordResetEmail(
            uiState.email.trim()
        )
            .addOnCompleteListener { task ->

                uiState = uiState.copy(
                    isLoading = false
                )

                if(task.isSuccessful) {
                    onSuccess()
                } else {
                    uiState = uiState.copy(
                        error = task.exception?.message
                    )
                }
            }
    }
}