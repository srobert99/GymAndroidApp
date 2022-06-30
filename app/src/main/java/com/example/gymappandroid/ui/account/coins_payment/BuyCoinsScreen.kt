package com.example.gymappandroid.ui.account.coins_payment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.MainTopAppBar
import com.example.gymappandroid.utils.DataStore


@Composable
fun BuyCoinsScreen(userDetailsViewModel: UserDetailsViewModel, navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val dataStore = DataStore(LocalContext.current)
    val userSession = dataStore.getUserSession.collectAsState(initial = "")

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopAppBar(
                detailsViewModel = userDetailsViewModel,
                navController = navController,
                isOnMainScreen = false
            )
        }
    ) {
        if (userSession.value == "") {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            BuyCoinsScreenContent(
                uid = userSession.value!!,
                userDetailsViewModel = userDetailsViewModel
            ) { uid, amount ->
                userDetailsViewModel.buyCoins(uid, amount)
            }
        }
    }
}