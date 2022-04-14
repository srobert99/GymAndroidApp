package com.example.gymappandroid.ui.menu.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappandroid.data.models.ProductCategory
import com.example.gymappandroid.data.repositories.ProductsDataRepository
import kotlinx.coroutines.launch

class ShopViewModel(productsDataRepository: ProductsDataRepository) : ViewModel() {

    var shopCategories = listOf<ProductCategory>()

    init {
        viewModelScope.launch {
            shopCategories = productsDataRepository.getProductCategories()
        }
    }
}