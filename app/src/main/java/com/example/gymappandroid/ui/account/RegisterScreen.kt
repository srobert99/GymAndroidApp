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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymappandroid.ui.commons.PasswordTextField
import com.example.gymappandroid.ui.commons.UserInfoBox

@Composable
fun RegisterScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    .align(CenterHorizontally)
                    .padding(vertical = 20.dp), Arrangement.SpaceBetween
            ) {
                UserInfoBox(labelText = "Full Name", leadingIcon = Icons.Filled.Person)
                UserInfoBox(
                    labelText = "Phone Number",
                    leadingIcon = Icons.Filled.Phone,
                    isNumber = true
                )
                UserInfoBox(labelText = "Email", leadingIcon = Icons.Filled.Email)
                PasswordTextField()
                PasswordTextField("Confirm Password")
            }
            Column(modifier = Modifier
                .weight(1f)
                .align(CenterHorizontally)
                .padding(top = 20.dp)) {
                Button(
                    onClick = { navController.popBackStack()},
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
