package com.example.gymappandroid.ui.menu.shop.checkout

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.gymappandroid.R
import com.example.gymappandroid.data.models.ShoppingCartItem
import com.example.gymappandroid.ui.menu.shop.ShopViewModel
import com.example.gymappandroid.ui.menu.shop.cart_screen.CartItemUI
import com.example.gymappandroid.utils.DataStore

@Composable
fun CheckoutScreenContent(shopViewModel: ShopViewModel) {
    val shoppingCartProduct by shopViewModel.shoppingCartProducts.collectAsState()
    val context = LocalContext.current
    val dataStore = DataStore(context)
    val userSession = dataStore.getUserSession.collectAsState(initial = "")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.dark_blue)),
    ) {
        LazyColumn() {
            items(shoppingCartProduct.size) { index ->
                CartItemUI(shoppingCartItem = shoppingCartProduct[index])
            }
        }
        Divider(color = Color.Black)
        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter), verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Total = ${calculateTotal(shoppingCartProduct)} STARS")
            Spacer(modifier = Modifier.weight(1f))
            Button({ onBuyPressed(shopViewModel, context, uid = userSession.value!!) }) {
                Text("Confirm")
            }
        }
    }
}

private fun onBuyPressed(shopViewModel: ShopViewModel, context: Context, uid: String) {
    shopViewModel.buyProducts(uid)
    shopViewModel.removeAllProductsFromShoppingCart(uid)
    Toast.makeText(context, "Purchase Successful", Toast.LENGTH_SHORT).show()
}


private fun calculateTotal(shoppingCartProducts: List<ShoppingCartItem>): Long =
    shoppingCartProducts.sumOf { it.price }