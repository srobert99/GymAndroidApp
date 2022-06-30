package com.example.gymappandroid.data.firestore.products_data_source

import com.example.gymappandroid.data.models.Order
import com.example.gymappandroid.data.models.Review
import com.example.gymappandroid.data.models.ShoppingCartItem
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreProductsDataSource {
    private var fireStoreDataBase = Firebase.firestore
    private var productsTypesDBReference = fireStoreDataBase.collection("products_categories")
    private val products = fireStoreDataBase.collection("products")
    private val ordersReference = fireStoreDataBase.collection("orders")
    private val shoppingCartDBReference = fireStoreDataBase.collection("shopping_cart")
    private val reviewsDBReference = fireStoreDataBase.collection("products_review")

    suspend fun getProductsType(): List<DocumentSnapshot> =
        productsTypesDBReference.get().await().documents

    suspend fun getProductsFromCategory(category: String): List<DocumentSnapshot> =
        products.whereEqualTo("product_category", category).get().await().documents

    suspend fun getProduct(productId: String): DocumentSnapshot =
        products.document(productId).get().await()

    suspend fun saveItemInShoppingCart(shoppingCartItem: ShoppingCartItem) {
        shoppingCartDBReference.add(shoppingCartItem).await()
    }

    suspend fun getShoppingCartItems(userId: String): List<DocumentSnapshot> =
        shoppingCartDBReference.whereEqualTo("userId", userId).get().await().documents

    fun removeShoppingCartItem(shoppingCartItemId: String) {
        shoppingCartDBReference.document(shoppingCartItemId).delete()
    }

    suspend fun removeAllShoppingCartItems(userId: String) {
        val x = shoppingCartDBReference.whereEqualTo("userId", userId).get().await().documents
        for (item in x) {
            if (item["userId"] == userId) {
                shoppingCartDBReference.document(item.id).delete()
            }
        }
    }

    fun addReview(review: Review) {
        reviewsDBReference.add(review)
    }

    suspend fun getProductReviews(productId: String): List<DocumentSnapshot> {
        return reviewsDBReference.whereEqualTo("product_id", productId).get().await().documents
    }

    suspend fun addOrder(order: Order): String =
        ordersReference.add(order).await().id
}