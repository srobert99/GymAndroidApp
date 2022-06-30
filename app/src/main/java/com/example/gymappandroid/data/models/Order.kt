package com.example.gymappandroid.data.models

data class Order(
    val list: List<OrderItem>,
    val userId: String,
    val orderTime:String
)