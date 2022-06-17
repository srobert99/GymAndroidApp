package com.example.gymappandroid.data.repositories

import com.example.gymappandroid.data.firestore.user_data_source.FireStoreUserDataSource
import com.example.gymappandroid.data.models.User

class UserDataRepository(private val fireStoreUserDataSource: FireStoreUserDataSource) {
    suspend fun createUserProfile(user: User) = fireStoreUserDataSource.createUserProfile(user)

    suspend fun editUserProfile(user: User) = fireStoreUserDataSource.updateUserProfile(user)

    suspend fun getUserProfile(uid: String) = fireStoreUserDataSource.getUserProfile(uid)

    suspend fun buyCoins(uid: String, amount: Int) =
        fireStoreUserDataSource.addCoins(uid, amount)
}