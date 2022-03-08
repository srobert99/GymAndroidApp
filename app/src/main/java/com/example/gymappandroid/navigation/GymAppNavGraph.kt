package com.example.gymappandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymappandroid.ui.account.auth.login.LoginScreen
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.account.auth.register.RegisterScreen
import com.example.gymappandroid.ui.account.auth.register.RegisterViewModel
import com.example.gymappandroid.ui.account.user_details.DetailsContent
import com.example.gymappandroid.ui.account.user_details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.main_menu.MainScreen
import com.example.gymappandroid.ui.menu.main_menu.MainScreenViewModel

@Composable
fun GymAppNavGraph(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    detailsViewModel: UserDetailsViewModel,
    mainScreenViewModel: MainScreenViewModel,
    isLogged: Boolean
) {
    val navController = rememberNavController()
    val firstPage: String = if (isLogged) {
        "main_screen"
    } else {
        "login_screen"
    }

    NavHost(navController = navController, startDestination = firstPage) {
        composable(Screen.Login.route) { LoginScreen(navController, loginViewModel) }
        composable(Screen.Register.route) { RegisterScreen(navController, registerViewModel) }
        composable(Screen.Details.route) {
            val email = it.arguments?.getString("email") ?: "null"
            DetailsContent(navController, detailsViewModel, email)
        }
        composable(Screen.Main.route) { MainScreen(mainScreenViewModel) }
    }
}
