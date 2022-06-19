package com.example.gymappandroid.ui.menu.shop.checkout

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.MainTopAppBar
import com.example.gymappandroid.ui.menu.shop.ShopViewModel

@Composable
fun CheckoutScreen(
    shopViewModel: ShopViewModel,
    navController: NavController,
    detailsViewModel: UserDetailsViewModel
) {
    Scaffold(
        topBar = {
            MainTopAppBar(
                detailsViewModel = detailsViewModel,
                isOnMainScreen = false,
                navController = navController
            )
        }
    ) {
        CheckoutScreenContent(navController = navController, shopViewModel = shopViewModel)
    }
}