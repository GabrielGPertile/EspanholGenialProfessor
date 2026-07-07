package com.example.espanholgenialprofessor.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espanholgenialprofessor.data.auth.AuthRepository
import com.example.espanholgenialprofessor.domain.auth.AuthState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _destination = MutableStateFlow(SplashDestination.LOADING)

    val destination: StateFlow<SplashDestination> = _destination

    init{
        startDelay()
    }

    private fun startDelay()
    {
        viewModelScope.launch {
            delay(800)

            authRepository.observeAuthState().collect { state ->
                _destination.value = when(state) {
                    AuthState.Authenticated -> SplashDestination.HOME
                    AuthState.Unauthenticated -> SplashDestination.LOGIN
                    AuthState.Loading -> SplashDestination.LOADING
                }
            }
        }
    }
}