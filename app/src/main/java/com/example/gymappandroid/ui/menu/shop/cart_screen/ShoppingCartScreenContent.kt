package com.example.gymappandroid.ui.menu.shop.cart_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.data.models.SizeOption

@Composable
fun ShoppingCartScreenContent() {

    val mockProducts = listOf(
        Product(
            model = "Adidas Predator x1",
            price = 299.99,
            availableSize = listOf(SizeOption("M")),
            image = listOf("https://images.dog.ceo/breeds/otterhound/n02091635_386.jpg")
        ),
        Product(
            model = "Nike",
            price = 300.00,
            availableSize = listOf(SizeOption("M")),
            image = listOf("https://images.dog.ceo/breeds/tervuren/shadow_and_lake.jpg")
        ),
        Product(
            model = "Kappa",
            price = 149.99,
            availableSize = listOf(SizeOption("M")),
            image = listOf("https://images.dog.ceo/breeds/pinscher-miniature/n02107312_867.jpg")
        )
    )

    Surface(color = Color.LightGray, modifier = Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(10.dp)) {
            items(mockProducts) {
                CartItemUI(product = it)
            }
        }
    }
}

@Composable
@Preview
private fun PreviewShoppingCartScreenContent() {
    ShoppingCartScreenContent()
}