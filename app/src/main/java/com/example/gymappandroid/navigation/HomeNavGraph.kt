package com.example.gymappandroid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.main_menu.MainScreen
import com.example.gymappandroid.ui.menu.profile.ProfileScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    userDetailsViewModel: UserDetailsViewModel
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
                userDetailsViewModel = userDetailsViewModel,
                navController = navController
            )
        }
    }
}