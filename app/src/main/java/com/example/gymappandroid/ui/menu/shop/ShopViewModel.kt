package com.example.gymappandroid.ui.menu.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.data.models.ProductCategory
import com.example.gymappandroid.data.repositories.ProductsDataRepository
import kotlinx.coroutines.launch

class ShopViewModel(val productsDataRepository: ProductsDataRepository) : ViewModel() {

    var shopCategories = listOf<ProductCategory>()

    var products = listOf<Product>()

    init {
        viewModelScope.launch {
            shopCategories = productsDataRepository.getProductCategories()
        }
    }

    suspend fun getProductsFromCategory(category: String) {
        products = productsDataRepository.getProductsFromCategory(category)
    }
}