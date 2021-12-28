package com.example.gymappandroid.data.firebase

import com.example.gymappandroid.ui.account.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
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

    fun register(user: User, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(user.email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful){
                    user.uid = currentUser()!!.uid
                    userDataBase.collection("user").document(user.uid).set(user).addOnSuccessListener {
                        emitter.onComplete()
                    }
                }
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}