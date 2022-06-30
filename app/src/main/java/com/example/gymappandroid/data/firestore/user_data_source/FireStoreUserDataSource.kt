package com.example.gymappandroid.data.firestore.user_data_source

import com.example.gymappandroid.data.models.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FireStoreUserDataSource {
    private var firesStoreDataBase = Firebase.firestore
    private var dataBaseReference = firesStoreDataBase.collection("user")

    suspend fun createUserProfile(user: User): String {
        return try {
            dataBaseReference.document(user.uid).set(user).await()
            "Success"
        } catch (e: FirebaseFirestoreException) {
            e.message.toString()
        }
    }

    suspend fun getUserProfile(uid: String): User? {
        return try {
            dataBaseReference.document(uid).get().await().toObject<User>()
        } catch (e: FirebaseFirestoreException) {
            null
        }
    }

    suspend fun updateUserProfile(user: User): String {
        return try {
            dataBaseReference.document(user.uid).set(user).await()
            "Success"
        } catch (e: FirebaseFirestoreException) {
            e.message.toString()
        }
    }

    suspend fun updatecoins(userId:String):DocumentSnapshot {
        return dataBaseReference.document(userId).get().await()
    }

    suspend fun deleteProfile(uid: String): String {
        return try {
            dataBaseReference.document(uid).delete().await()
            "Success"
        } catch (e: FirebaseFirestoreException) {
            e.message.toString()
        }
    }

    suspend fun addCoins(uid: String, amount: Int) {
        dataBaseReference.document(uid).update("balance", amount).await()

    }

    suspend fun spendCoins(uid: String, amount: Int) {
        dataBaseReference.document(uid).update("balance", amount).await()
    }
}