package com.example.gymappandroid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.shop.ProductDetailsScreen
import com.example.gymappandroid.ui.menu.shop.ProductsScreen
import com.example.gymappandroid.ui.menu.shop.ShopScreen
import com.example.gymappandroid.ui.menu.shop.ShopViewModel

fun NavGraphBuilder.shopNavGraph(
    navController: NavController,
    shopViewModel: ShopViewModel,
    detailsViewModel: UserDetailsViewModel
) {
    navigation(
        startDestination = Screen.Shop.route,
        route = SHOP_GRAPH_ROUTE,
    ) {
        composable(
            route = Screen.Shop.route,
        ) {
            ShopScreen(shopViewModel, navController, detailsViewModel)
        }
        composable(
            route = Screen.Products.route + "/{product_category}"
        ) {
            val itemCategory = it.arguments?.getString("product_category") ?: "shoes"
            ProductsScreen(
                shopViewModel = shopViewModel,
                navController = navController,
                detailsViewModel = detailsViewModel,
                itemCategory = itemCategory
            )
        }
        composable(
            route = Screen.ProductDetails.route + "/{product_id}",
        ) {
            val productId = it.arguments?.getString("product_id") ?: "a"
            ProductDetailsScreen(
                shopViewModel = shopViewModel,
                productId = productId,
                detailsViewModel = detailsViewModel,
                navController = navController
            )
        }
    }
}