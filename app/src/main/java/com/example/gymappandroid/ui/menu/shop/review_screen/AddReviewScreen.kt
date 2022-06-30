package com.example.gymappandroid.ui.menu.shop.review_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.MainTopAppBar
import com.example.gymappandroid.ui.menu.shop.ShopViewModel

@Composable
fun AddReviewScreen(
    detailsViewModel: UserDetailsViewModel,
    navController: NavController,
    shopViewModel: ShopViewModel,
) {
    val name by detailsViewModel.name.observeAsState("")
    val surname by detailsViewModel.surname.observeAsState("")

    Scaffold(
        topBar = {
            MainTopAppBar(
                detailsViewModel = detailsViewModel,
                isOnMainScreen = false,
                navController = navController
            )
        },
        content = {
            AddReviewScreenContent(shopViewModel, name, surname, navController)
        }
    )

}