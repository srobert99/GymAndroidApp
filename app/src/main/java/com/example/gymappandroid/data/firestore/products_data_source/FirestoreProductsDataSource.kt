package com.example.gymappandroid.data.firestore.products_data_source

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreProductsDataSource {
    private var fireStoreDataBase = Firebase.firestore
    private var productsTypesDBReference = fireStoreDataBase.collection("products_categories")
    private var productsSnapshot = mutableListOf<QueryDocumentSnapshot>()

    suspend fun getProductsType(): List<DocumentSnapshot> =
        productsTypesDBReference.get().await().documents
}