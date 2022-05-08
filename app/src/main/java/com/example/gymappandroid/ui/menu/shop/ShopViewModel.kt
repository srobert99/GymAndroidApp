package com.example.gymappandroid.ui.menu.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.data.models.ProductCategory
import com.example.gymappandroid.data.repositories.ProductsDataRepository
import kotlinx.coroutines.launch

class ShopViewModel(val productsDataRepository: ProductsDataRepository) : ViewModel() {

    var shopCategories = listOf<ProductCategory>()

    var products = listOf<Product>()

    var selectedProduct = Product()

     var _isOnLoadingSate = MutableLiveData(true)
    val isOnLoadingState: LiveData<Boolean> = _isOnLoadingSate

    init {
        viewModelScope.launch {
            shopCategories = productsDataRepository.getProductCategories()
        }
    }

    suspend fun getProductsFromCategory(category: String) {
        products = productsDataRepository.getProductsFromCategory(category)
    }

    suspend fun getProductDetails(productId: String) {
        selectedProduct = productsDataRepository.getProductDetails(productId)
        load()
    }

    private fun load() {
        _isOnLoadingSate.value = !(_isOnLoadingSate.value!!)
    }
}