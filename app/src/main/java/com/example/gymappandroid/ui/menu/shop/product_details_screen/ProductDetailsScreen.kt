package com.example.gymappandroid.ui.menu.shop.product_details_screen

import android.annotation.SuppressLint
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
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.commons.LoadingScreen
import com.example.gymappandroid.ui.menu.MainTopAppBar
import com.example.gymappandroid.ui.menu.shop.ProductDetailsContent
import com.example.gymappandroid.ui.menu.shop.ShopViewModel
import com.example.gymappandroid.utils.DataStore

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProductDetailsScreen(
    shopViewModel: ShopViewModel,
    productId: String,
    detailsViewModel: UserDetailsViewModel,
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val dataStoreInstance = DataStore(LocalContext.current)
    val userId by dataStoreInstance.getUserSession.collectAsState(initial = "null")
    val currentSelectedProduct by shopViewModel.currentSelectedProduct.collectAsState()
    val productReviews by shopViewModel.reviews.collectAsState()

    LaunchedEffect(key1 = productId) {
        shopViewModel.resetSelectedProduct()
        shopViewModel.getProductDetails(productId)
        shopViewModel.getProductReviews(productId)
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
            if (currentSelectedProduct != Product()) {
                ProductDetailsContent(
                    userId!!,
                    shopViewModel,
                    currentSelectedProduct,
                    productReviews,
                    navController
                )
            } else {
                LoadingScreen()
            }
        }
    )
}
