package com.example.gymappandroid.data.models

data class Product(
    val productType: ProductType = ProductType.SNEAKER,
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val price: Double = 0.00,
    val stock: Int = 0
)


enum class ProductType {
    SNEAKER,
    PROTEIN,
    SHAKER,
    UNKNOWN
}