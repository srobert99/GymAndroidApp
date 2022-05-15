package com.example.gymappandroid.data.models

data class ShoppingCartItem(
    val itemId: String = "",
    val userId: String = "",
    val model: String = "",
    val imageSource: String = "",
    val price: Double = 0.00,
    val specification: String = ""
)