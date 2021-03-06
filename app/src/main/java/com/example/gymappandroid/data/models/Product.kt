package com.example.gymappandroid.data.models

import java.io.Serializable

data class Product(
    val id: String = "",
    val productType: String = "",
    val model: String = "",
    val description: String = "",
    val image: List<String> = listOf(),
    val price: Long = 0,
    val availableSize: List<SizeOption> = listOf()
) : Serializable