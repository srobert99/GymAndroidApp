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
fun ProductsScreen(
    shopViewModel: ShopViewModel,
    navController: NavController,
    detailsViewModel: UserDetailsViewModel,
    itemCategory: String
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    LaunchedEffect(Unit) {
        shopViewModel.getProductsFromCategory(itemCategory)
    }

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            MainTopAppBar(
                detailsViewModel = detailsViewModel,
                isOnMainScreen = false,
                navController = navController
            )
        },
        content = {
            ProductsListContent(
                shopViewModel = shopViewModel,
                navController = navController
            )
        }
    )
}