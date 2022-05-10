package com.example.gymappandroid.ui.menu.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.data.models.ProductCategory
import com.example.gymappandroid.data.models.ShoppingCartItem
import com.example.gymappandroid.data.repositories.ProductsDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShopViewModel(val productsDataRepository: ProductsDataRepository) : ViewModel() {

    var shopCategories = listOf<ProductCategory>()

    var products = listOf<Product>()

    private var _selectedProduct = MutableStateFlow(Product())
    var selectedProduct: StateFlow<Product> = _selectedProduct

    private var _shoppingCartItems = MutableStateFlow(mutableListOf<ShoppingCartItem>())
    var shoppingCartItems: StateFlow<List<ShoppingCartItem>> = _shoppingCartItems

    private var _selectedSizeName = MutableStateFlow("")
    var selectedSize: StateFlow<String> = _selectedSizeName


    init {
        viewModelScope.launch {
            shopCategories = productsDataRepository.getProductCategories()
        }
    }

    suspend fun getProductsFromCategory(category: String) {
        products = productsDataRepository.getProductsFromCategory(category)
    }

    fun addItemToShoppingCart() {
        _shoppingCartItems.value.add(ShoppingCartItem(selectedProduct.value, selectedSize.value))
    }

    suspend fun getProductDetails(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedProduct.value = productsDataRepository.getProductDetails(productId)
        }
    }

    fun selectSize(sizeName: String) {
        _selectedSizeName.value = sizeName
    }

    fun resetSelectedSize() {
        _selectedSizeName.value = ""
    }

}