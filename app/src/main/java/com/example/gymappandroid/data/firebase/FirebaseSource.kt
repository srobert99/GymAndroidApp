package com.example.gymappandroid.data.firebase

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseSource {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    suspend fun login(email: String, password: String): String {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            "Success"
        } catch (e: FirebaseException) {
            e.message.toString()
        }
    }

    suspend fun register(email: String, password: String): String {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            "Success"
        } catch (e: FirebaseException) {
            e.message.toString()
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}