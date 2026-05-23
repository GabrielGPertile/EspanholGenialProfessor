package com.example.espanholgenialprofessor.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> = _isReady

    //função que inicializa o delay
    init{
        startDelay()
    }

    private fun startDelay()
    {
        viewModelScope.launch {
            //Configura delay de 2 segundos
            delay(2000)

            _isReady.value = true
        }
    }
}