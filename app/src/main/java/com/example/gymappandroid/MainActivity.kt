package com.example.gymappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gymappandroid.di.appModule
import com.example.gymappandroid.di.authViewModelModule
import com.example.gymappandroid.ui.account.auth.AuthViewModel
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
        val authViewModel = getViewModel<AuthViewModel>()

        setContent {
            GymAppAndroidTheme {
                GymAppNavGraph(authViewModel)
            }
        }
    }
}

