package com.example.gymappandroid

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

@Composable
fun GymAppNavGraph(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    detailsViewModel: UserDetailsViewModel,
    isLogged: Boolean
) {
    val navController = rememberNavController()
    val firstPage: String = if (isLogged) {
        "main_screen"
    } else {
        "login_screen"
    }

    NavHost(navController = navController, startDestination = firstPage) {
        composable("login_screen") { LoginScreen(navController, loginViewModel) }
        composable("register_screen") { RegisterScreen(navController, registerViewModel) }
        composable("details_screen/{email}") {
            val email = it.arguments?.getString("email")?:"null"
            DetailsContent(navController, detailsViewModel, email)
        }
        composable("main_screen") { MainPage(loginViewModel, navController) }
    }
}
