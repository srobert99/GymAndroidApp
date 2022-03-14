package com.example.gymappandroid.ui.account.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymappandroid.R
import com.example.gymappandroid.navigation.Screen
import com.example.gymappandroid.ui.commons.PasswordTextField
import com.example.gymappandroid.ui.commons.UserInfoBox
import com.example.gymappandroid.utils.DataStore
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    val email by loginViewModel.email.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    val firebaseResponse by loginViewModel.firebaseStatus.observeAsState("")
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val dataStore = DataStore(context)
    var loading by remember { mutableStateOf(false) }

    val loginUser: () -> Unit = {
        coroutineScope.launch {
            loading = true
            loginViewModel.login()
            if (firebaseResponse == "Success") {
                dataStore.saveUserSession(loginViewModel.getUID() ?: "")
                loginViewModel.getUID()
                navController.navigate(Screen.Main.route)
            } else {
                Toast.makeText(context, firebaseResponse, Toast.LENGTH_SHORT).show()
            }
            loading = false
        }
    }

    Scaffold {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.app_padding))
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .size(dimensionResource(id = R.dimen.logo_size))
                )
                Column(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.login_page_data))
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.login_page_title),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    )
                    Text(
                        "Please sign in to continue",
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 20.dp),
                    )
                    UserInfoBox(
                        labelText = "Email",
                        leadingIcon = Icons.Filled.Email,
                        isNumber = false,
                        readOnly = false,
                        onValueChange = { loginViewModel.onEmailChange(it) },
                        currentText = email
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    PasswordTextField(
                        currentText = password,
                        onPasswordChange = { loginViewModel.onPasswordChange(it) })
                    if (!loading) {
                        Button(
                            onClick = { loginUser() },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 30.dp)
                                .size(height = 50.dp, width = 200.dp),
                        ) {
                            Text(
                                "LOGIN",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.dark_blue)
                            )
                        }
                    } else {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 30.dp)
                                .size(50.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(
                            "Forgot Password?",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 20.dp),
                            textDecoration = TextDecoration.Underline
                        )
                        Row {
                            Text("Don't have an account? ")
                            Text(
                                "Sign up",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        Screen.Register.route
                                    )
                                }, color = colorResource(id = R.color.teal_200)
                            )
                        }
                    }
                }
            }
        }
    }
}