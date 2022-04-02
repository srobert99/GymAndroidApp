package com.example.gymappandroid.ui.menu.main_menu

import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.MainTopAppBar

@Composable
fun MainScreen(
    detailsViewModel: UserDetailsViewModel,
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MainTopAppBar(detailsViewModel = detailsViewModel) },
        content = {
            MainScreenContent(
                userDetailsScreenViewModel = detailsViewModel,
                navController = navController
            )
        }
    )
}
