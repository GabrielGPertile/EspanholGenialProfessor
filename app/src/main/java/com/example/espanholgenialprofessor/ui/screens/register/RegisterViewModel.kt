package com.example.espanholgenialprofessor.ui.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    var uiState by mutableStateOf(RegisterUiState())
        private set

    fun onEmailChange(value: String) {
        uiState = uiState.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        uiState = uiState.copy(password = value)
    }

    fun onConfirmPasswordChange(value: String) {
        uiState = uiState.copy(confirmPassword = value)
    }

    fun register(onSuccess: () -> Unit) {

        if(uiState.email.isBlank() || uiState.password.isBlank() || uiState.confirmPassword.isBlank()){
            uiState = uiState.copy(
                error = "Email, senha e confirmação de senha são obrigatórios."
            )

            return
        }

        if(uiState.password != uiState.confirmPassword) {
            uiState = uiState.copy(
                error = "As senhas não coincidem."
            )

            return
        }

        uiState = uiState.copy(
            isLoading = true,
            error = null
        )

        firebaseAuth.createUserWithEmailAndPassword(
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