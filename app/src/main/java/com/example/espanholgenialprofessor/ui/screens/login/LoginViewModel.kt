package com.example.espanholgenialprofessor.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espanholgenialprofessor.data.auth.AuthRepository
import com.example.espanholgenialprofessor.domain.auth.AuthValidator
import com.example.espanholgenialprofessor.domain.user.UserProfile
import com.example.espanholgenialprofessor.domain.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val authValidator: AuthValidator
) : ViewModel()  {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(value: String) {
        uiState = uiState.copy(
            email = value,
            error = null
        )
    }

    fun onPasswordChange(value: String) {
        uiState = uiState.copy(
            password = value,
            error = null
        )
    }

    fun login(onSuccess: (UserProfile) -> Unit) {
        val emailError = authValidator.validateEmail(uiState.email)

        if (emailError != null) {
            uiState = uiState.copy(
                error = emailError
            )

            return
        }

        val passwordError = authValidator.validatePassword(uiState.password)

        if (passwordError != null) {
            uiState = uiState.copy(
                error = passwordError
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
            onSuccess = { uid ->

                viewModelScope.launch {
                    val result = userRepository.getProfile(uid)

                    result.onSuccess { userProfile ->

                        if (userProfile != null) {
                            uiState = uiState.copy(
                                isLoading = false,
                                error = null
                            )

                            onSuccess(userProfile)
                        } else {
                            uiState = uiState.copy(
                                isLoading = false,
                                error = "Perfil não encontrado"
                            )
                        }
                    }

                    result.onFailure { exception ->
                        uiState = uiState.copy(
                            isLoading = false,
                            error = exception.message
                        )
                    }

                }

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