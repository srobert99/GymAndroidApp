package com.example.gymappandroid.ui.account

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
    val name by authViewModel.name.observeAsState("")
    val email by authViewModel.email.observeAsState("")
    val password by authViewModel.password.observeAsState("")
    val cPassword by authViewModel.confirmedPassword.observeAsState("")
    val phoneNumber by authViewModel.phoneNumber.observeAsState("")

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally
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
                    .padding(20.dp), Arrangement.SpaceBetween
            ) {
                UserInfoBox(
                    labelText = "Full Name",
                    leadingIcon = Icons.Filled.Person,
                    isNumber = false,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { authViewModel.onNameChange(it) },
                    currentText = name
                )
                UserInfoBox(
                    labelText = "Phone Number",
                    leadingIcon = Icons.Filled.Phone,
                    isNumber = false,
                    currentText = phoneNumber,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { authViewModel.onPhoneNumberChange(it) }
                )
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
                    onClick = { authViewModel.signup() },
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
