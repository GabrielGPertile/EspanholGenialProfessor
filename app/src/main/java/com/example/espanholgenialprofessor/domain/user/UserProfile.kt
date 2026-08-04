package com.example.espanholgenialprofessor.domain.user

data class UserProfile(
    val uid: String = "",
    val name: String? = null,
    val role: UserRole = UserRole.STUDENT,
    val photoUrl: String? = null
)