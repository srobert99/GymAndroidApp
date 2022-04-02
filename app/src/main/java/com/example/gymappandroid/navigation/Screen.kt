package com.example.gymappandroid.navigation

const val MAIN_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Details : Screen("details_screen/{email}")
    object Main : Screen("main_screen")
    object Profile : Screen("profile_screen")
}