package com.example.gymappandroid.data.repositories

import com.example.gymappandroid.data.firebase.FirebaseSource
import com.example.gymappandroid.ui.account.model.User

class UserRepository(private val firebase: FirebaseSource) {
    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(user: User, password: String) = firebase.register(user, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()
}