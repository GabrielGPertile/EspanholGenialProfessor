package com.example.espanholgenialprofessor.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(value: String) {
        uiState = uiState.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        uiState = uiState.copy(password = value)
    }

    fun login(onSuccess: () -> Unit) {

        if(uiState.email.isBlank() || uiState.password.isBlank()) {
            uiState = uiState.copy(
                error = "Informe um e-mail e senha"
            )
            return
        }

        uiState = uiState.copy(
            isLoading = true,
            error = null
        )

        firebaseAuth.signInWithEmailAndPassword(
            uiState.email.trim(),
            uiState.password.trim()
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