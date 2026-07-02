package com.example.espanholgenialprofessor.ui.screens.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel : ViewModel() {
    private val _destination = MutableStateFlow(SplashDestination.LOADING)

    val destination: StateFlow<SplashDestination> = _destination

    //função que inicializa o delay
    init{
        startDelay()
    }

    private fun startDelay()
    {
        viewModelScope.launch {
            delay(800)

            val user = FirebaseAuth.getInstance().currentUser

            _destination.value = if (user != null) {
                SplashDestination.HOME
            } else {
                SplashDestination.LOGIN
            }

            Log.d("AUTH", FirebaseAuth.getInstance().currentUser?.uid.toString())
        }
    }
}