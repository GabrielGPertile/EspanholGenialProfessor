package com.example.espanholgenialprofessor.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.espanholgenialprofessor.data.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel()  {
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

        authRepository.login(
            uiState.email,
            uiState.password,
            onSuccess = {
                uiState = uiState.copy(
                    isLoading = false
                )

                onSuccess()
            },
            onError = { message ->
                uiState = uiState.copy(
                    isLoading = false,
                    error = message
                )
            }
        )
    }
}