package com.example.espanholgenialprofessor.domain.user

interface UserRepository {
    suspend fun saveUserProfile(
        userProfile : UserProfile
    ) : Result<Unit>

    suspend fun getProfile(
        uid : String
    ) : Result<UserProfile?>

    suspend fun updatePhoto(
        uid : String,
        photoUrl : String
    ) : Result<Unit>
}