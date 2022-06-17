package com.example.gymappandroid.navigation

const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"
const val SHOP_GRAPH_ROUTE = "shop"

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Details : Screen("details_screen/{email}")
    object Main : Screen("main_screen")
    object Profile : Screen("profile_screen")
    object Shop : Screen("shop_screen")
    object Products : Screen("products_screen")
    object ProductDetails : Screen("product_details_screen")
    object ShoppingCart : Screen("shopping_cart_screen")
    object BuyCoins : Screen("buy_coins_screen")
}