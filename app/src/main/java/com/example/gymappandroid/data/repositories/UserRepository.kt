package com.example.gymappandroid.data.repositories

import com.example.gymappandroid.data.firebase.FirebaseSource
import com.example.gymappandroid.data.models.User

class UserRepository(private val firebase: FirebaseSource) {
    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun saveUserData(user: User) = firebase.saveUserData(user)

    fun getUserData(uid: String) = firebase.getUserData(uid)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()
}