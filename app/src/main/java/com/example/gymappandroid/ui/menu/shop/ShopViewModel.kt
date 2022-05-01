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

    private var _isOnLoadingSate = MutableLiveData(false)
    val isOnLoadingState: LiveData<Boolean> = _isOnLoadingSate

    private var _selectedProduct = MutableLiveData(Product())
    val selectedProduct: LiveData<Product> = _selectedProduct

    init {
        viewModelScope.launch {
            shopCategories = productsDataRepository.getProductCategories()
        }
    }

    suspend fun getProductsFromCategory(category: String) {
        products = productsDataRepository.getProductsFromCategory(category)
    }

    suspend fun getProductDetails(productId: String) {
        load()
        _selectedProduct.value = productsDataRepository.getProduct(productId)
        load()
    }

    private fun load() {
        _isOnLoadingSate.value = !(_isOnLoadingSate.value!!)
    }
}