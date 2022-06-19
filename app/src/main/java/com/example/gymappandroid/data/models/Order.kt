package com.example.gymappandroid.data.models

data class Order(
    val list:List<ShoppingCartItem>,
    val userId:String
)