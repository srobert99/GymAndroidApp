package com.example.gymappandroid.ui.menu.shop.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymappandroid.ui.menu.shop.ShopViewModel

@Composable
fun ShoppingCartScreenContent(shopViewModel: ShopViewModel) {

    val shoppingCartItems by shopViewModel.shoppingCartItems.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        if (shoppingCartItems.isEmpty()) {
            Text(
                "No items added yet",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        LazyColumn(contentPadding = PaddingValues(10.dp)) {
            items(shoppingCartItems) {
                CartItemUI(shoppingCartItem = it)
            }
        }
        Text(
            "To remove items from the cart swipe item to the left side",
            color = Color.Gray,
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        )
    }
}