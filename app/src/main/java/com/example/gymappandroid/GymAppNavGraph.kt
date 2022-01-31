package com.example.gymappandroid

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymappandroid.ui.account.auth.login.LoginScreen
import com.example.gymappandroid.ui.account.auth.register.RegisterScreen
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.account.user_details.DetailsContent

@Composable
fun GymAppNavGraph(loginViewModel: LoginViewModel, isLogged:Boolean) {
    val navController = rememberNavController()
    val firstPage:String = if(isLogged){
        "main_screen"
    }else{
        "login_screen"
    }

    NavHost(navController = navController, startDestination = firstPage) {
        composable("login_screen") { LoginScreen(navController, loginViewModel) }
        composable("register_screen") { RegisterScreen(navController, loginViewModel) }
        composable("details_screen") { DetailsContent(navController, loginViewModel) }
        composable("main_screen") { MainPage(loginViewModel,navController) }
    }
}
