package com.example.gymappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gymappandroid.di.appModule
import com.example.gymappandroid.di.viewModelModule
import com.example.gymappandroid.navigation.SetupNavGraph
import com.example.gymappandroid.ui.account.auth.details.DetailsContent
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.account.auth.register.RegisterScreen
import com.example.gymappandroid.ui.account.auth.register.RegisterViewModel
import com.example.gymappandroid.ui.menu.shop.ShopViewModel
import com.example.gymappandroid.ui.theme.GymAppAndroidTheme
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(appModule, viewModelModule))
        }

        val loginViewModel = getViewModel<LoginViewModel>()
        val registerViewModel = getViewModel<RegisterViewModel>()
        val detailsViewModel = getViewModel<UserDetailsViewModel>()
        val shopViewModel = getViewModel<ShopViewModel>()
        val isLogged = loginViewModel.isLogged.value ?: false

        setContent {
            GymAppAndroidTheme {
                navController = rememberNavController()

                SetupNavGraph(
                    navController = navController,
                    isUserLoggedIn = isLogged,
                    registerViewModel = registerViewModel,
                    loginViewModel = loginViewModel,
                    detailsViewModel = detailsViewModel,
                    shopViewModel = shopViewModel
                )
            }
        }
    }
}
