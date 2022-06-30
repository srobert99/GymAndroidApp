package com.example.gymappandroid.ui.menu.shop.OrderInfo

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.MainTopAppBar
import com.example.gymappandroid.ui.menu.shop.ShopViewModel

@Composable
fun OrderInfoScreen(
    detailsViewModel: UserDetailsViewModel,
    shopViewModel: ShopViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            MainTopAppBar(
                detailsViewModel = detailsViewModel,
                isOnMainScreen = false,
                navController = navController,
                isOnOrderDetailScreen = true
            )
        }
    ) {
        OrderInfoScreenContent(shopViewModel.orderId)
    }
}