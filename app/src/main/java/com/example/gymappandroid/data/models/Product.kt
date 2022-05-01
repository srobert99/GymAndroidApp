package com.example.gymappandroid.data.models

data class Product(
    val id: String = "",
    val productType: String,
    val model: String = "",
    val description: String = "",
    val image: String = "",
    val price: Double = 0.00,
    val stock: HashMap<String, Int> = hashMapOf()
)