package com.example.espanholgenialprofessor.data.user

import com.example.espanholgenialprofessor.domain.user.UserProfile
import com.example.espanholgenialprofessor.domain.user.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun saveUserProfile(userProfile: UserProfile): Result<Unit> {

        return try {
            firestore.collection("users")
                .document(userProfile.uid)
                .set(userProfile)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProfile(uid: String): Result<UserProfile?> {

        return try {
            val document = firestore.collection("users")
                .document(uid)
                .get()
                .await()

            if (document.exists()) {
                val userProfile = document.toObject(UserProfile::class.java)
                Result.success(userProfile)
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePhoto(uid: String, photoUrl: String): Result<Unit> {

        return try {
            firestore.collection("users")
                .document(uid)
                .update("photoUrl", photoUrl)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}