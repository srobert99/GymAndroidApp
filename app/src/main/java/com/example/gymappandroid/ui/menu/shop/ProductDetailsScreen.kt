package com.example.gymappandroid.ui.menu.shop

import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.MainTopAppBar

@Composable
fun ProductDetailsScreen(
    shopViewModel: ShopViewModel,
    productId: String,
    detailsViewModel: UserDetailsViewModel,
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    LaunchedEffect(Unit) {
        shopViewModel.getProductDetails(productId)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopAppBar(
                detailsViewModel = detailsViewModel,
                isOnMainScreen = false,
                navController = navController
            )
        },
        content = {
            ProductDetailsContent(shopViewModel = shopViewModel)
        }
    )
}
