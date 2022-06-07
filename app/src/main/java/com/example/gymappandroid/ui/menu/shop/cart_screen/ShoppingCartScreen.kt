package com.example.gymappandroid.ui.menu.shop.cart_screen

import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.commons.LoadingScreen
import com.example.gymappandroid.ui.menu.MainTopAppBar
import com.example.gymappandroid.ui.menu.shop.ShopViewModel
import com.example.gymappandroid.utils.DataStore

@Composable
fun ShoppingCartScreen(
    detailsViewModel: UserDetailsViewModel,
    navController: NavController,
    shopViewModel: ShopViewModel
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val dataStoreInstance = DataStore(LocalContext.current)
    val userId = dataStoreInstance.getUserSession.collectAsState(initial = "null")

    LaunchedEffect(userId) {
        shopViewModel.getShoppingCartItems(userId.value!!)
    }

    val shoppingListItems by shopViewModel.shoppingCartProducts.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopAppBar(
                detailsViewModel = detailsViewModel,
                isOnMainScreen = false,
                navController = navController
            )
        }
    ) {
        if (userId.value == "null") {
            LoadingScreen()
        } else {
            ShoppingCartScreenContent(
                shoppingCartProducts = shoppingListItems
            ) { shopViewModel.removeItemFromShoppingCart(it, userId = userId.value!!) }
        }
    }
}