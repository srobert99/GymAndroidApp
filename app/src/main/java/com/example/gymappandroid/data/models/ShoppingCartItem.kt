package com.example.gymappandroid.data.models

data class ShoppingCartItem(
    val shoppingCartItemId: String = "",
    val itemId: String = "",
    val userId: String = "",
    val model: String = "",
    val imageSource: String = "",
    val price: Long = 0,
    val specification: String = ""
)