package com.example.gymappandroid.data.repositories

import com.example.gymappandroid.data.firestore.products_data_source.FirestoreProductsDataSource
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.data.models.ProductCategory
import com.google.firebase.firestore.DocumentSnapshot

class ProductsDataRepository(val firestoreProductsDataSource: FirestoreProductsDataSource) {

    suspend fun getProductCategories(): List<ProductCategory> =
        firestoreProductsDataSource.getProductsType().map {
            convertDocToProductCategory(it)
        }

    suspend fun getProductDetails(productId: String): Product =
        convertDocToProduct(firestoreProductsDataSource.getProduct(productId))

    suspend fun getProductsFromCategory(category: String): List<Product> =
        firestoreProductsDataSource.getProductsFromCategory(category).map {
            convertDocToProduct(it)
        }

    private fun convertDocToProductCategory(productCategoryDocSS: DocumentSnapshot): ProductCategory =
        ProductCategory(
            productCategoryDocSS.id,
            productCategoryDocSS["product_category"].toString(),
            productCategoryDocSS["image"].toString()
        )

    @Suppress("UNCHECKED_CAST")
    private fun convertDocToProduct(productDocSS: DocumentSnapshot): Product {

        return Product(
            id = productDocSS.id,
            productType = productDocSS["product_category"].toString(),
            model = productDocSS["model"].toString(),
            description = productDocSS["description"].toString(),
            image = getImages(productDocSS["image"] as ArrayList<String>),
            price = productDocSS["price"] as Double,
            stock = productDocSS["stock"] as HashMap<String, Int>
        )
    }

    private fun getImages(images: ArrayList<String>): List<String> {
        val listOfImages = mutableListOf<String>()
        for (image in images) {
            listOfImages.add(image)
        }
        return listOfImages
    }
}


