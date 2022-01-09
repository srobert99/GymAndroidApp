package com.example.gymappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymappandroid.di.appModule
import com.example.gymappandroid.di.authViewModelModule
import com.example.gymappandroid.ui.account.auth.AuthViewModel
import com.example.gymappandroid.ui.account.user_details.DetailsContent
import com.example.gymappandroid.ui.theme.GymAppAndroidTheme
import com.google.firebase.auth.FirebaseUser
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
        val isLogged = (authViewModel.firebaseUser != null)

        setContent {
            GymAppAndroidTheme {
                GymAppNavGraph(authViewModel = authViewModel, isLogged = isLogged)
            }
        }
    }
}

@Composable
fun MainPage(authViewModel: AuthViewModel, navController: NavController) {
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hello Roberto")
            Button(
                onClick = { logout(authViewModel, navController) },
                modifier = Modifier.padding(100.dp)
            ) {
                Text("Logout")
            }
        }
    }
}

private fun logout(authViewModel: AuthViewModel, navController: NavController) {
    authViewModel.logout()
    navController.navigate("login_screen")
}
