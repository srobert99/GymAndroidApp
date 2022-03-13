package com.example.gymappandroid.ui.account.user_details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.gymappandroid.ui.commons.DateTextField
import com.example.gymappandroid.ui.commons.RoundedToggleButton
import com.example.gymappandroid.ui.commons.UserInfoBox
import com.example.gymappandroid.ui.theme.GymAppAndroidTheme
import com.example.gymappandroid.utils.DataStore
import kotlinx.coroutines.launch

@Composable
fun DetailsContent(
    navController: NavController?,
    detailsViewModel: UserDetailsViewModel,
    userEmail: String
) {
    GymAppAndroidTheme {
        Scaffold {
            val firestoreResponse by detailsViewModel.firestoreStatus.observeAsState("")
            val isMale by detailsViewModel.isMale.observeAsState(true)
            val phoneNumber by detailsViewModel.phoneNumber.observeAsState("")
            val name by detailsViewModel.name.observeAsState("")
            val birthdate by detailsViewModel.birthdate.observeAsState("")
            val surname by detailsViewModel.surname.observeAsState("")
            var image by remember { mutableStateOf(R.drawable.detailspatgemale) }
            var isLoading by remember { mutableStateOf(false) }
            val context = LocalContext.current
            val coroutineScope = rememberCoroutineScope()
            val dataStore = DataStore(context)
            val userSession = dataStore.getUserSession.collectAsState(initial = "")

            val saveUserData: () -> Unit = {
                coroutineScope.launch {
                    isLoading = true
                    detailsViewModel.saveUserProfile(userSession.value, userEmail)
                    if (firestoreResponse == "Success") {
                        navController?.navigate("main_screen")
                    } else {
                        Toast.makeText(context, firestoreResponse, Toast.LENGTH_SHORT).show()
                    }
                    isLoading = false
                }

            }

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
                                detailsViewModel.onGenderSelection(true)
                            },
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .weight(1f)
                        )
                        RoundedToggleButton(
                            state = !isMale,
                            text = "Female",
                            onClick = {
                                detailsViewModel.onGenderSelection(false)
                            },
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        )
                    }
                    UserInfoBox(
                        labelText = "Name",
                        readOnly = false,
                        leadingIcon = Icons.Filled.Person,
                        onValueChange = { detailsViewModel.onNameChange(it) },
                        currentText = name,
                    )
                    UserInfoBox(
                        labelText = "Surname",
                        readOnly = false,
                        leadingIcon = Icons.Filled.Person,
                        onValueChange = { detailsViewModel.onSurnameChange(it) },
                        currentText = surname,
                    )
                    UserInfoBox(
                        labelText = "Phone number",
                        readOnly = false,
                        leadingIcon = Icons.Filled.Phone,
                        onValueChange = { detailsViewModel.onPhoneNumberChange(it) },
                        currentText = phoneNumber,
                        isNumber = true
                    )
                    DateTextField(
                        labelText = "Select birthdate",
                        readOnly = false,
                        onValueChange = { detailsViewModel.onBirthDateSelect(it) },
                        currentText = birthdate,
                        leadingIcon = Icons.Filled.DateRange
                    )
                    if (!isLoading) {
                        Button(
                            onClick = { saveUserData() },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 30.dp)
                                .size(height = 50.dp, width = 200.dp),
                        ) {
                            Text("DONE")
                        }
                    } else {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 30.dp)
                                .size(50.dp)
                        )
                    }
                }
            }
        }
    }
}

