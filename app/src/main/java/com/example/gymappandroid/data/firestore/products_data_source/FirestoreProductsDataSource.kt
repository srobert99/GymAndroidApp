package com.example.gymappandroid.data.firestore.products_data_source

import com.example.gymappandroid.data.models.Product
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreProductsDataSource {
    private var fireStoreDataBase = Firebase.firestore
    private var productsTypesDBReference = fireStoreDataBase.collection("products_categories")
    private val products = fireStoreDataBase.collection("products")
    private var productsSnapshot = mutableListOf<QueryDocumentSnapshot>()

    suspend fun getProductsType(): List<DocumentSnapshot> =
        productsTypesDBReference.get().await().documents

    suspend fun getProductsFromCategory(category: String): List<DocumentSnapshot> =
        products.whereEqualTo("product_category", category).get().await().documents

    suspend fun getProduct(productId: String): DocumentSnapshot =
        products.document(productId).get().await()
}