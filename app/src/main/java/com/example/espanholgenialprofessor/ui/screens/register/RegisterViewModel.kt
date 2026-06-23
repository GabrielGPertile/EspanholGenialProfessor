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

    fun register(onSuccess: () -> Unit) {

        if(uiState.email.isBlank() or uiState.password.isBlank()){
            uiState = uiState.copy(
                error = "Informe seu email ou senha!"
            )

            return
        }

        uiState.copy(
            isLoading = true,
            error = null
        )

        firebaseAuth.createUserWithEmailAndPassword(
            uiState.email.trim(),
            uiState.password.trim()
        )
            .addOnCompleteListener { task ->

                uiState.copy(
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