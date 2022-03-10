package com.example.gymappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.gymappandroid.di.appModule
import com.example.gymappandroid.di.authViewModelModule
import com.example.gymappandroid.navigation.GymAppNavGraph
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.account.auth.register.RegisterViewModel
import com.example.gymappandroid.ui.account.user_details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.TopMenuBar
import com.example.gymappandroid.ui.menu.main_menu.MainScreenViewModel
import com.example.gymappandroid.ui.theme.GymAppAndroidTheme
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(appModule, authViewModelModule))
        }

        val authViewModel = getViewModel<LoginViewModel>()
        val registerViewModel = getViewModel<RegisterViewModel>()
        val detailsViewModel = getViewModel<UserDetailsViewModel>()
        val mainScreenViewModel = getViewModel<MainScreenViewModel>()
        val isLogged = (authViewModel.firebaseUser != null)

        setContent {
            GymAppAndroidTheme {
                GymAppNavGraph(
                    loginViewModel = authViewModel,
                    registerViewModel = registerViewModel,
                    detailsViewModel = detailsViewModel,
                    mainScreenViewModel = mainScreenViewModel,
                    isLogged = false
                )
            }
        }
    }
}
