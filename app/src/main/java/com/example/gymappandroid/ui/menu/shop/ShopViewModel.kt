package com.example.gymappandroid.ui.menu.shop

import com.example.gymappandroid.data.models.Product

class ShopViewModel {

    val shopItems = mutableListOf<Product>()

    init {
        for (i in 0..20) {
            shopItems.add(i, Product("", "", "", "", 30.00, 300))
        }
    }
}