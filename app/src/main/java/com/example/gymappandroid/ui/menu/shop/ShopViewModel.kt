package com.example.gymappandroid.ui.menu.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.data.models.ProductCategory
import com.example.gymappandroid.data.models.Review
import com.example.gymappandroid.data.models.ShoppingCartItem
import com.example.gymappandroid.data.repositories.ProductsDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShopViewModel(private val productsDataRepository: ProductsDataRepository) : ViewModel() {

    var shopCategories = listOf<ProductCategory>()

    var products = listOf<Product>()

    var orderId: String = ""

    private var _shoppingCartProducts = MutableStateFlow(mutableListOf(ShoppingCartItem()))
    val shoppingCartProducts: StateFlow<List<ShoppingCartItem>> = _shoppingCartProducts

    private var _currentSelectedProduct = MutableStateFlow(Product())
    val currentSelectedProduct: StateFlow<Product> = _currentSelectedProduct

    private var _reviews = MutableStateFlow(listOf(Review()))
    val reviews: StateFlow<List<Review>> = _reviews

    private var _currentSelectedProductSpecification = MutableStateFlow("")
    var currentSelectedProductSpecification: StateFlow<String> =
        _currentSelectedProductSpecification

    init {
        viewModelScope.launch {
            shopCategories = productsDataRepository.getProductCategories()
        }
    }

    suspend fun getProductsFromCategory(category: String) {
        products = productsDataRepository.getProductsFromCategory(category)
    }

    fun removeAllProductsFromShoppingCart(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            productsDataRepository.removeShoppingCartItems(userId)
        }
    }

    fun addItemToShoppingCart(userId: String) {
        viewModelScope.launch {
            productsDataRepository.addItemToShoppingList(
                ShoppingCartItem(
                    "",
                    currentSelectedProduct.value.id,
                    userId = userId,
                    currentSelectedProduct.value.model,
                    currentSelectedProduct.value.image.first(),
                    currentSelectedProduct.value.price,
                    currentSelectedProductSpecification.value
                )
            )
        }
    }

    fun getProductDetails(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentSelectedProduct.value =
                productsDataRepository.getProductDetails(productId)
        }
    }

    fun getProductReviews(productId: String) {
        viewModelScope.launch {
            _reviews.value = productsDataRepository.getProductReviews(productId)
        }
    }

    fun buyProducts(userId: String) {
        viewModelScope.launch {
            orderId =
                productsDataRepository.createOrder(shoppingCartProducts.value, userId)
        }
    }

    fun addReview(review: Review) {
        productsDataRepository.addReview(review)
    }

    fun getShoppingCartItems(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _shoppingCartProducts.value = productsDataRepository.getShoppingListItems(userId)
        }
    }

    fun selectSize(sizeName: String) {
        _currentSelectedProductSpecification.value = sizeName
    }

    fun removeItemFromShoppingCart(shoppingCartItemId: String, userId: String) {
        productsDataRepository.removeShoppingCartItem(shoppingCartItemId)
        _shoppingCartProducts.value = mutableListOf()
        //refresh shopping list
        getShoppingCartItems(userId)
    }

    fun resetSelectedProduct() {
        _currentSelectedProduct.value = Product()
        _currentSelectedProductSpecification.value = ""
    }

}