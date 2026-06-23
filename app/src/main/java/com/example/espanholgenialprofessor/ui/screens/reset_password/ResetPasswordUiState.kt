package com.example.espanholgenialprofessor.ui.screens.reset_password

data class ResetPasswordUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
