package com.example.gymappandroid.ui.account.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymappandroid.ui.account.auth.AuthViewModel
import com.example.gymappandroid.ui.commons.PasswordTextField
import com.example.gymappandroid.ui.commons.UserInfoBox

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {
    val email by authViewModel.email.observeAsState("")
    val password by authViewModel.password.observeAsState("")
    val cPassword by authViewModel.confirmedPassword.observeAsState("")

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
        ) {
            Text(
                text = "Create Account",
                fontSize = 40.sp,
                modifier = Modifier.padding(top = 30.dp),
                color = Color.White
            )
            Text(text = "Please fill the input below here", color = Color.Gray)
            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(20.dp),
                Arrangement.SpaceEvenly
            ) {
                UserInfoBox(
                    labelText = "Email",
                    leadingIcon = Icons.Filled.Email,
                    isNumber = false,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { authViewModel.onEmailChange(it) },
                    currentText = email
                )
                PasswordTextField(
                    currentText = password,
                    modifier = Modifier.fillMaxWidth(),
                    onPasswordChange = { authViewModel.onPasswordChange(it) })
                PasswordTextField(
                    labelText = "Confirm Password",
                    currentText = cPassword,
                    modifier = Modifier.fillMaxWidth(),
                    onPasswordChange = { authViewModel.onCPasswordChange(it) })
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp)
            ) {
                Button(
                    onClick = { registerUser(authViewModel, navController) },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(height = 50.dp, width = 200.dp)
                ) {
                    Text("Register")
                }
                Text(
                    buildAnnotatedString {
                        append("Already have an account?")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Magenta,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Log in")
                        }
                    }
                )
            }
        }
    }
}

private fun registerUser(authViewModel: AuthViewModel, navController: NavController) {
    authViewModel.signup()
    navController.navigate("details_screen")
}
