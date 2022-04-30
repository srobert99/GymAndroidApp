package com.example.gymappandroid.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gymappandroid.ui.account.auth.details.DetailsContent
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.account.auth.login.LoginScreen
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.account.auth.register.RegisterScreen
import com.example.gymappandroid.ui.account.auth.register.RegisterViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    detailsViewModel: UserDetailsViewModel
) {
    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable(
            route = Screen.Register.route
        ) {
            RegisterScreen(navController = navController, registerViewModel = registerViewModel)
        }
        composable(
            route = Screen.Details.route,
        ) {
            val email = it.arguments?.getString("email") ?: "null"
            DetailsContent(
                navController = navController,
                detailsViewModel = detailsViewModel,
                userEmail = email
            )
        }
    }
}
