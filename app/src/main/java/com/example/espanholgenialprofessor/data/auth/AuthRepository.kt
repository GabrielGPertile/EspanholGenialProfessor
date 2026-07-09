package com.example.espanholgenialprofessor.data.auth

import com.example.espanholgenialprofessor.domain.auth.AuthState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepository @Inject constructor() {
    private val auth = FirebaseAuth.getInstance()

    fun observeAuthState() : Flow<AuthState> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser

            val state =  if (user != null) {
                AuthState.Authenticated
            } else {
                AuthState.Unauthenticated
            }

            trySend(state)
        }

        auth.addAuthStateListener(listener)

        awaitClose {
            auth.removeAuthStateListener(listener)
        }
    }

    fun login(
        email : String,
        password : String,
        onSuccess : () -> Unit,
        onError : (String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(
            email.trim(),
            password.trim()
        )
            .addOnCompleteListener { task ->

                if(task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message)
                }
            }
    }

    fun logout() {
        auth.signOut()
    }
}