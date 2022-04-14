package com.example.gymappandroid.data.repositories

import com.example.gymappandroid.data.firestore.products_data_source.FirestoreProductsDataSource
import com.example.gymappandroid.data.models.ProductCategory
import com.google.firebase.firestore.DocumentSnapshot

class ProductsDataRepository(val firestoreProductsDataSource: FirestoreProductsDataSource) {

    suspend fun getProductCategories(): List<ProductCategory> =
        firestoreProductsDataSource.getProductsType().map {
            convertDocToProductCategory(it)
        }

    private fun convertDocToProductCategory(productCategoryDocSS: DocumentSnapshot): ProductCategory =
        ProductCategory(
            productCategoryDocSS.id,
            productCategoryDocSS["product_category"].toString(),
            productCategoryDocSS["image"].toString()
        )
}


