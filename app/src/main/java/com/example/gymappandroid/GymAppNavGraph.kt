package com.example.gymappandroid

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymappandroid.ui.account.LoginScreen
import com.example.gymappandroid.ui.account.RegisterScreen
import com.example.gymappandroid.ui.account.auth.AuthViewModel

@Composable
fun GymAppNavGraph(authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_screen"){
        composable("login_screen"){ LoginScreen(navController,authViewModel)}
        composable("register_screen"){ RegisterScreen(navController,authViewModel)}
    }
}
