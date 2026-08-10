package com.example.espanholgenialprofessor.ui.screens.teacher

import androidx.lifecycle.ViewModel
import com.example.espanholgenialprofessor.data.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeTeacherViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel(){

    fun logout() {
        authRepository.logout()
    }
}