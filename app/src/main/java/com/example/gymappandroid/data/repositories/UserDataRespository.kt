package com.example.gymappandroid.data.repositories

import com.example.gymappandroid.data.firestore.FireStoreSource
import com.example.gymappandroid.data.models.User

class UserDataRepository(private val fireStoreSource: FireStoreSource) {
    suspend fun createUserProfile(user: User) = fireStoreSource.createUserProfile(user)

    suspend fun editUserProfile(user: User) = fireStoreSource.updateUserProfile(user)

    suspend fun getUserProfile(uid: String) = fireStoreSource.getUserProfile(uid)
}