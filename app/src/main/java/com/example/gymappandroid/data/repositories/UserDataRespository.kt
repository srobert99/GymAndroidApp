package com.example.gymappandroid.data.repositories

import com.example.gymappandroid.data.firestore.user_data_source.FireStoreUserDataSource
import com.example.gymappandroid.data.models.User
import com.google.firebase.firestore.DocumentSnapshot

class UserDataRepository(private val fireStoreUserDataSource: FireStoreUserDataSource) {
    suspend fun createUserProfile(user: User) = fireStoreUserDataSource.createUserProfile(user)

    suspend fun editUserProfile(user: User) = fireStoreUserDataSource.updateUserProfile(user)

    suspend fun getUserProfile(uid: String) = fireStoreUserDataSource.getUserProfile(uid)

    suspend fun updatecoins(uid: String) = fireStoreUserDataSource.updatecoins(uid).getCoins()
    suspend fun buyCoins(uid: String, amount: Int) =
        fireStoreUserDataSource.addCoins(uid, amount)

    suspend fun spendCoins(uid: String, amount: Int) =
        fireStoreUserDataSource.spendCoins(uid, amount)

    private fun DocumentSnapshot.getCoins(): String {
        return this["balance"].toString()
    }

}