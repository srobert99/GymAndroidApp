package com.example.gymappandroid.ui.account.user_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymappandroid.R
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.commons.DatePickerField
import com.example.gymappandroid.ui.commons.RoundedToggleButton
import com.example.gymappandroid.ui.commons.UserInfoBox
import com.example.gymappandroid.ui.theme.GymAppAndroidTheme

@Composable
fun DetailsContent(navController: NavController, loginViewModel: LoginViewModel) {
    GymAppAndroidTheme {
        Scaffold {
            val isMale by loginViewModel.isMale.observeAsState(true)
            val phoneNumber by loginViewModel.phoneNumber.observeAsState("")
            val birthDate by loginViewModel.birthDate.observeAsState("")
            val name by loginViewModel.name.observeAsState("")
            val lastname by loginViewModel.lastname.observeAsState("")
            var image by remember { mutableStateOf(R.drawable.detailspatgemale) }

            image =
                if (isMale) R.drawable.detailspatgemale else R.drawable.detailspagefemale

            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        "WELCOME \n WE WOULD LIKE TO KNOW MORE ABOUT YOU",
                        fontWeight = Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center

                    )
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(dimensionResource(id = R.dimen.logo_size))
                            .padding(bottom = 10.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        RoundedToggleButton(
                            state = isMale,
                            text = "Male",
                            onClick = {
                                loginViewModel.onGenderSelection(true)
                            },
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .weight(1f)
                        )
                        RoundedToggleButton(
                            state = !isMale,
                            text = "Female",
                            onClick = {
                                loginViewModel.onGenderSelection(false)
                            },
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        )
                    }
                    UserInfoBox(
                        labelText = "Name",
                        leadingIcon = Icons.Filled.Person,
                        onValueChange = { loginViewModel.onNameChange(it) },
                        currentText = name,
                    )
                    UserInfoBox(
                        labelText = "Surname",
                        leadingIcon = Icons.Filled.Person,
                        onValueChange = { loginViewModel.onLastNameChange(it) },
                        currentText = lastname,
                    )
                    UserInfoBox(
                        labelText = "Phone number",
                        leadingIcon = Icons.Filled.Phone,
                        onValueChange = { loginViewModel.onPhoneNumberChange(it) },
                        currentText = phoneNumber,
                        isNumber = true
                    )
                    DatePickerField(
                        context = LocalContext.current,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                    Button(
                        onClick = { saveUserData(loginViewModel, navController) },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 30.dp)
                            .size(height = 50.dp, width = 200.dp),
                    ) {
                        Text("DONE")
                    }
                }
            }
        }
    }
}

private fun saveUserData(loginViewModel: LoginViewModel, navController: NavController) {
    loginViewModel.saveUserData()
    //navController.navigate("main_screen")
}

