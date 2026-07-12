package com.example.espanholgenialprofessor.domain.auth

import javax.inject.Inject

class AuthValidator @Inject constructor() {

    private companion object {
        const val MAX_EMAIL_LENGTH = 254
        const val MIN_PASSWORD_LENGTH = 6
        const val MAX_PASSWORD_LENGTH = 64
    }

    private val emailRegex = Regex(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    )

    fun validateEmail(email: String) : String? {
        if (email.isBlank()) {
            return "Informe um e-mail!"
        }

        if (email != email.trim()) {
            return "O e-mail não pode começar ou terminar com espaços."
        }

        if (email.length > MAX_EMAIL_LENGTH) {
            return "O e-mail não pode ter mais que $MAX_EMAIL_LENGTH caracteres."
        }

        if (!emailRegex.matches(email)) {
            return "Informe um e-mail válido."
        }

        return null
    }

    fun validatePassword(password: String, fieldName : String = "senha"): String? {
        if (password.isBlank()) {
            return "Informe uma $fieldName!"
        }

        if (password != password.trim()) {
            return "A $fieldName não pode começar ou terminar com espaços."
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return "A $fieldName deve ter pelo menos $MIN_PASSWORD_LENGTH caracteres."
        }

        if (password.length > MAX_PASSWORD_LENGTH) {
            return "A $fieldName pode ter no máximo $MAX_PASSWORD_LENGTH caracteres."
        }

        return null
    }

    fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ) : String? {
        val confirmPasswordError = validatePassword(
            confirmPassword,
            "confirmação de senha"
        )

        if (confirmPasswordError != null) {
            return confirmPasswordError
        }

        if (password != confirmPassword) {
            return "As senhas não coincidem."
        }

        return null
    }
}