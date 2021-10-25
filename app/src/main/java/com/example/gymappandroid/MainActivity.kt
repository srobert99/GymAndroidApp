package com.example.gymappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymappandroid.ui.theme.GymAppAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen()
}


@Composable
fun UserInfoBox(labelText: String, leadingIcon: ImageVector, isPassword: Boolean = false) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .padding(10.dp),
        label = { Text(labelText) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        singleLine = true,
    )
}

@Composable
fun PasswordTextField() {
    Column() {
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.design_ic_visibility)
        else
            painterResource(id = R.drawable.design_ic_visibility_off)

        OutlinedTextField(
            value = password,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            onValueChange = {
                password = it
            },
            placeholder = { Text(text = "Password") },
            label = { Text(text = "Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()
        )
    }
}

@Composable
fun LoginScreen() {
    GymAppAndroidTheme {
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
                            .fillMaxSize()
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
                        UserInfoBox("Email", Icons.Filled.Email)
                        PasswordTextField()

                        Button(
                            onClick = {}, shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 60.dp)
                                .size(height = 50.dp, width = 200.dp),
                        ) {
                            Text(
                                "LOGIN",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Forgot Password?",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(bottom = 20.dp)
                            )
                            Text(
                                buildAnnotatedString {
                                    append("Don't have an account? ")
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("Sign up")
                                    }
                                }, modifier = Modifier.padding(top = 10.dp), color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }

}

