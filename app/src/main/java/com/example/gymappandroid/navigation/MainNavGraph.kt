package com.example.gymappandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.account.auth.register.RegisterViewModel
import com.example.gymappandroid.ui.menu.shop.ShopViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    isUserLoggedIn: Boolean,
    registerViewModel: RegisterViewModel,
    loginViewModel: LoginViewModel,
    detailsViewModel: UserDetailsViewModel,
    shopViewModel: ShopViewModel
) {
    val startDestination = if (isUserLoggedIn) {
        HOME_GRAPH_ROUTE
    } else {
        AUTH_GRAPH_ROUTE
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authNavGraph(navController, loginViewModel, registerViewModel, detailsViewModel)
        homeNavGraph(navController, detailsViewModel)
        shopNavGraph(navController, shopViewModel, detailsViewModel)
    }
}
