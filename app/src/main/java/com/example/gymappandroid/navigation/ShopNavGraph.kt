package com.example.gymappandroid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.shop.CategoryScreen
import com.example.gymappandroid.ui.menu.shop.OrderInfo.OrderInfoScreen
import com.example.gymappandroid.ui.menu.shop.ProductsScreen
import com.example.gymappandroid.ui.menu.shop.ShopViewModel
import com.example.gymappandroid.ui.menu.shop.checkout.CheckoutScreen
import com.example.gymappandroid.ui.menu.shop.product_details_screen.ProductDetailsScreen
import com.example.gymappandroid.ui.menu.shop.review_screen.AddReviewScreen

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
            CategoryScreen(shopViewModel, navController, detailsViewModel)
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
        composable(
            route = Screen.AddReview.route
        ) {
            AddReviewScreen(
                detailsViewModel = detailsViewModel,
                navController = navController,
                shopViewModel = shopViewModel
            )
        }
        composable(
            route = Screen.Checkout.route
        ) {
            CheckoutScreen(
                shopViewModel = shopViewModel,
                navController = navController,
                detailsViewModel = detailsViewModel
            )
        }
        composable(
            route = Screen.OrderInfo.route,
        ) {
            OrderInfoScreen(
                detailsViewModel = detailsViewModel,
                shopViewModel = shopViewModel,
                navController = navController
            )
        }
    }
}