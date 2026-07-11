package com.example.espanholgenialprofessor.ui.screens.reset_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.espanholgenialprofessor.data.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
  private val authRepository: AuthRepository
) : ViewModel() {
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

        authRepository.sendResetPasswordEmail(
            uiState.email,
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