package com.example.espanholgenialprofessor.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espanholgenialprofessor.domain.user.UserProfile
import com.example.espanholgenialprofessor.domain.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    var userProfile by mutableStateOf<UserProfile?>(null)
        private set

    fun loadProfile()
    {
        val uid = auth.currentUser?.uid ?: return

        viewModelScope.launch {
            userRepository.getProfile(uid)
                .onSuccess { profile ->
                    userProfile = profile
                }
        }
    }
}