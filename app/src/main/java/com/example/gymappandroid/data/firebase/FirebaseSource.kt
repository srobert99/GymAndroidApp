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

//    fun login(email: String, password: String) = Completable.create { emitter ->
//        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//            if (!emitter.isDisposed) {
//                if (it.isSuccessful)
//                    emitter.onComplete()
//                else
//                    emitter.onError(it.exception!!)
//            }
//        }
//    }

    suspend fun login(email: String, password: String): Boolean {
        delay(3000L)
        return true
//        return try {
//            delay(3000L)
//            firebaseAuth.signInWithEmailAndPassword(email, password).await()
//            true
//        } catch (e: Exception) {
//            false
//        }
    }

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()

            } else
                emitter.onError(it.exception!!)
        }
    }

//    fun saveUserData(newUser: User) {
//        userDataBase.collection("user")
//            .add(newUser)
//            .addOnSuccessListener {
//                Log.d("Firestore", "it works")
//            }.addOnFailureListener {
//                Log.d("Firestore", "it didn't work")
//            }
//    }

    suspend fun saveUserData(newUser: User): Boolean {
        return try {
            userDataBase.collection("user")
                .add(newUser)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getUserData(uid: String): User? {
        val userRef = userDataBase.collection("users").document(uid)
        var user: User? = null
        userRef.get().addOnSuccessListener {
            user = it.toObject<User>()
        }
        return user
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}