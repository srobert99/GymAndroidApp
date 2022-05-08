package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
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
    val loadingState by shopViewModel.isOnLoadingState.observeAsState(true)

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
            if (loadingState) {
                Box(
                    Modifier
                        .background(Color.White)
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center),
                        color = Color.Black
                    )
                }
            } else {
                ProductDetailsContent(shopViewModel)
            }
        }
    )
}
