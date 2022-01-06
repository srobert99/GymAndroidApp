package com.example.gymappandroid.data.firebase

import android.util.Log
import com.example.gymappandroid.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable

class FirebaseSource {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val userDataBase = Firebase.firestore

    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
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

    fun saveUserData(newUser: User) {
        userDataBase.collection("user")
            .add(newUser)
            .addOnSuccessListener {
                Log.d("Firestore", "it works")
            }.addOnFailureListener {
                Log.d("Firestore", "it didn't work")
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