package com.example.gymappandroid.data.repositories

import com.example.gymappandroid.data.firebase.FirebaseSource
import com.example.gymappandroid.data.models.User

class UserRepository(private val firebase: FirebaseSource) {
    suspend fun login(email: String, password: String) = firebase.login(email, password)

    suspend fun register(email: String, password: String) = firebase.register(email, password)

    suspend fun saveUserData(user: User) = firebase.saveUserData(user)

    suspend fun getUserData(uid: String) = firebase.getUserData(uid)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()
}