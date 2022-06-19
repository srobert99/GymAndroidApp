package com.example.gymappandroid.ui.menu.shop.checkout

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.gymappandroid.data.models.ShoppingCartItem
import com.example.gymappandroid.ui.menu.shop.ShopViewModel
import com.example.gymappandroid.ui.menu.shop.cart_screen.CartItemUI

@Composable
fun CheckoutScreenContent(navController: NavController, shopViewModel: ShopViewModel) {
    val shoppingCartProduct by shopViewModel.shoppingCartProducts.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.scrollable(
                rememberScrollState(),
                orientation = Orientation.Vertical
            )
        ) {
            for (item in shoppingCartProduct) {
                CartItemUI(shoppingCartItem = item)
            }

            Divider()

            Row(Modifier.fillMaxWidth()) {
                Text("Total = ${calculateTotal(shoppingCartProduct)}")
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {}) {
                    "Proceed"
                }
            }
        }
    }
}


private fun calculateTotal(shoppingCartProducts: List<ShoppingCartItem>): Double =
    shoppingCartProducts.sumOf { it.price }