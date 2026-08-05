package com.example.espanholgenialprofessor.ui.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espanholgenialprofessor.data.auth.AuthRepository
import com.example.espanholgenialprofessor.domain.auth.AuthValidator
import com.example.espanholgenialprofessor.domain.user.UserProfile
import com.example.espanholgenialprofessor.domain.user.UserRepository
import com.example.espanholgenialprofessor.domain.user.UserRole
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val authValidator: AuthValidator
) : ViewModel() {
    var uiState by mutableStateOf(RegisterUiState())
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

    fun onConfirmPasswordChange(value: String) {
        uiState = uiState.copy(
            confirmPassword = value,
            error = null
        )
    }

    fun register(onSuccess: () -> Unit) {

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

        val confirmPasswordError = authValidator.validateConfirmPassword(uiState.password, uiState.confirmPassword)

        if (confirmPasswordError != null) {
            uiState = uiState.copy(
                error = confirmPasswordError
            )

            return
        }

        uiState = uiState.copy(
            isLoading = true,
            error = null
        )

        authRepository.register(
            uiState.email,
            uiState.password,
            onSuccess = { uid ->
                viewModelScope.launch {
                    val userProfile = UserProfile(
                        uid = uid,
                        name = null,
                        role = UserRole.STUDENT,
                        photoUrl = null
                    )

                    val result = userRepository.saveUserProfile(userProfile)

                    result.onSuccess {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = null
                        )

                        onSuccess()
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