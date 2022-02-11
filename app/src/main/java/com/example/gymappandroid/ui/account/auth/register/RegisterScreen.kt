package com.example.gymappandroid.ui.account.auth.register

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymappandroid.ui.commons.PasswordTextField
import com.example.gymappandroid.ui.commons.UserInfoBox
import com.example.gymappandroid.utils.DataStore
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController, registerViewModel: RegisterViewModel) {
    val email by registerViewModel.email.observeAsState("")
    val password by registerViewModel.password.observeAsState("")
    val cPassword by registerViewModel.confirmPassword.observeAsState("")
    val firebaseResponse by registerViewModel.firebaseStatus.observeAsState("")
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val dataStore = DataStore(context)

    val registerUser: () -> Unit = {
        coroutineScope.launch {
            isLoading = true
            registerViewModel.register()
            if (firebaseResponse == "Success") {
                dataStore.saveUserSession(registerViewModel.getFirebaseUID() ?: "")
                navController.navigate("details_screen/$email")
            } else {
                Toast.makeText(context, firebaseResponse, Toast.LENGTH_SHORT).show()
            }
            isLoading = false
        }

    }

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
                    onValueChange = { registerViewModel.onEmailChange(it) },
                    currentText = email
                )
                PasswordTextField(
                    currentText = password,
                    modifier = Modifier.fillMaxWidth(),
                    onPasswordChange = { registerViewModel.onPasswordChange(it) })
                PasswordTextField(
                    labelText = "Confirm Password",
                    currentText = cPassword,
                    modifier = Modifier.fillMaxWidth(),
                    onPasswordChange = { registerViewModel.onConfirmPasswordChange(it) })
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp)
            ) {
                if (!isLoading) {
                    Button(
                        onClick = { registerUser() },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.size(height = 50.dp, width = 200.dp)
                    ) {
                        Text("Register")
                    }
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 30.dp)
                            .size(50.dp)
                    )
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
