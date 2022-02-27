package com.example.gymappandroid.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Details : Screen("details_screen/{email}")
    object Main : Screen("main_screen")
}