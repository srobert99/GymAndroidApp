package com.example.gymappandroid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.main_menu.MainScreen
import com.example.gymappandroid.ui.menu.profile.ProfileScreen
import com.example.gymappandroid.ui.menu.shop.ShopViewModel
import com.example.gymappandroid.ui.menu.shop.cart_screen.ShoppingCartScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    userDetailsViewModel: UserDetailsViewModel,
    shopViewModel:ShopViewModel
) {
    navigation(
        startDestination = Screen.Main.route,
        route = HOME_GRAPH_ROUTE,
    ) {
        composable(
            route = Screen.Main.route,
        ) {
            MainScreen(
                userDetailsViewModel,
                navController
            )
        }
        composable(
            route = Screen.Profile.route
        ) {
            ProfileScreen(
                detailsViewModel = userDetailsViewModel,
                navController = navController
            )
        }
        composable(
            route = Screen.ShoppingCart.route
        ) {
            ShoppingCartScreen(
                detailsViewModel = userDetailsViewModel,
                navController = navController,
                shopViewModel = shopViewModel
            )
        }
    }
}