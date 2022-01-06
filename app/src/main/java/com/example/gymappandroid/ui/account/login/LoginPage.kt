package com.example.gymappandroid.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymappandroid.R
import com.example.gymappandroid.ui.account.auth.AuthViewModel
import com.example.gymappandroid.ui.commons.PasswordTextField
import com.example.gymappandroid.ui.commons.UserInfoBox

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    val email by authViewModel.email.observeAsState("")
    val password by authViewModel.password.observeAsState("")
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
                        .align(Alignment.CenterHorizontally)
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
                        onValueChange = { authViewModel.onEmailChange(it) },
                        currentText = email
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    PasswordTextField(
                        currentText = password,
                        onPasswordChange = { authViewModel.onPasswordChange(it) })
                    Button(
                        onClick = { authViewModel.login() },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 30.dp)
                            .size(height = 50.dp, width = 200.dp),
                    ) {
                        Text(
                            "LOGIN",
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.dark_blue)
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Forgot Password?",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 20.dp),
                            textDecoration = TextDecoration.Underline
                        )
                        Text(
                            buildAnnotatedString {
                                append("Don't have an account? ")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = colorResource(id = R.color.teal_200)
                                    ),
                                ) {
                                    append(" Sign up")
                                }
                            }, modifier = Modifier
                                .padding(top = 10.dp)
                                .clickable { navController.navigate("details_screen") },
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}