package com.example.gymappandroid.data.firebase

import android.util.Log
import com.example.gymappandroid.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FirebaseSource {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val userDataBase = Firebase.firestore

    suspend fun login(email: String, password: String): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun register(email: String, password: String): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            Log.d("firebase register", e.message.toString())
            false
        }
    }

    suspend fun saveUserData(newUser: User): Boolean {
        return try {
            userDataBase.collection("user")
                .add(newUser)
                .await()
            true
        } catch (e: Exception) {
            Log.d("firestore_save_user_data", e.message.toString())
            false
        }
    }

    suspend fun getUserData(uid: String): User? {
        val userRef = userDataBase.collection("users").document(uid)
        return try {
            userRef.get().await().toObject<User>()
        } catch (e: Exception) {
            Log.d("firestore_get_user", e.message.toString())
            return null
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}