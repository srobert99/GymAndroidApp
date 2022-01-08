package com.example.gymappandroid.ui.account.user_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.gymappandroid.R
import com.example.gymappandroid.ui.account.auth.AuthViewModel
import com.example.gymappandroid.ui.commons.DateTextField
import com.example.gymappandroid.ui.commons.RoundedToggleButton
import com.example.gymappandroid.ui.commons.UserInfoBox
import com.example.gymappandroid.ui.theme.GymAppAndroidTheme

@Composable
fun DetailsContent(navController: NavController, authViewModel: AuthViewModel) {
    GymAppAndroidTheme {
        Scaffold {
            val isMale by authViewModel.isMale.observeAsState(true)
            val phoneNumber by authViewModel.phoneNumber.observeAsState("")
            val birthDate by authViewModel.birthDate.observeAsState("")
            val name by authViewModel.name.observeAsState("")
            val lastname by authViewModel.lastname.observeAsState("")
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
                                authViewModel.onGenderSelection(true)
                            },
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .weight(1f)
                        )
                        RoundedToggleButton(
                            state = !isMale,
                            text = "Female",
                            onClick = {
                                authViewModel.onGenderSelection(false)
                            },
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        )
                    }
                    UserInfoBox(
                        labelText = "Name",
                        leadingIcon = Icons.Filled.Person,
                        onValueChange = { authViewModel.onNameChange(it) },
                        currentText = name,
                    )
                    UserInfoBox(
                        labelText = "Surname",
                        leadingIcon = Icons.Filled.Person,
                        onValueChange = { authViewModel.onLastNameChange(it) },
                        currentText = lastname,
                    )
                    UserInfoBox(
                        labelText = "Phone number",
                        leadingIcon = Icons.Filled.Phone,
                        onValueChange = { authViewModel.onPhoneNumberChange(it) },
                        currentText = phoneNumber,
                        isNumber = true
                    )
                    DateTextField(
                        labelText = "Birthdate",
                        leadingIcon = Icons.Filled.DateRange,
                        onValueChange = { authViewModel.onBirthDateChange(it) },
                        currentText = birthDate
                    )
                    Button(
                        onClick = { saveUserData(authViewModel,navController) },
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

private fun saveUserData(authViewModel: AuthViewModel, navController: NavController) {
//    authViewModel.saveUserData()
    navController.navigate("main_screen")
}

