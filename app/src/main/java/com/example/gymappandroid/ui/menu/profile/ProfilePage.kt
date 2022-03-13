package com.example.gymappandroid.ui.menu.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gymappandroid.R
import com.example.gymappandroid.ui.account.user_details.UserDetailsViewModel
import com.example.gymappandroid.ui.commons.DateTextField
import com.example.gymappandroid.ui.commons.UserInfoBox
import com.example.gymappandroid.utils.DataStore
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(userDetailsViewModel: UserDetailsViewModel) {
    val context = LocalContext.current
    val dataStore = DataStore(context)
    val coroutineScope = rememberCoroutineScope()
    val phoneNumber by userDetailsViewModel.phoneNumber.observeAsState("")
    val name by userDetailsViewModel.name.observeAsState("")
    val birthdate by userDetailsViewModel.birthdate.observeAsState("")
    val surname by userDetailsViewModel.surname.observeAsState("")
    var readOnly by remember { mutableStateOf(true) }
    var initLoading by remember { mutableStateOf(true) }
    var saveLoading by remember { mutableStateOf(false) }
    val firestoreResponse by userDetailsViewModel.firestoreStatus.observeAsState("")
    val userSession = dataStore.getUserSession.collectAsState(initial = "")

    fun initScreen() {
        if (userSession.value != "") {
            coroutineScope.launch {
                userDetailsViewModel.getUserProfile(userSession.value!!)
                initLoading = false
            }
            if (firestoreResponse != "") {
                Toast.makeText(context, firestoreResponse, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveProfile() {
        if (!readOnly) {
            saveLoading = true
            coroutineScope.launch {
                userDetailsViewModel.saveUserProfile(userSession.value)
                saveLoading = false
            }
            if (firestoreResponse != "") {
                Toast.makeText(context, firestoreResponse, Toast.LENGTH_SHORT).show()
            }
        }
        readOnly = !readOnly
    }

    initScreen()

    Scaffold {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (initLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(150.dp)
                            .padding(top = 100.dp)
                    )
                } else {
                    Column(
                        Modifier
                            .wrapContentSize()
                            .weight(0.3f)
                    ) {
                        //Profile picture
                        Image(
                            painter = painterResource(R.drawable.detailspatgemale),
                            contentDescription = "Contact profile picture",
                            modifier = Modifier
                                .size(150.dp)
                                .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                                .clip(CircleShape)
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .weight(0.6f), verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        UserInfoBox(
                            labelText = "Name",
                            leadingIcon = Icons.Filled.Person,
                            onValueChange = { userDetailsViewModel.onNameChange(it) },
                            readOnly = readOnly,
                            currentText = name,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledTextColor = Color.White,
                                disabledBorderColor = Color.White,
                                disabledLabelColor = Color.White,
                                disabledLeadingIconColor = Color.White
                            )
                        )
                        UserInfoBox(
                            labelText = "Surname",
                            leadingIcon = Icons.Filled.Person,
                            onValueChange = { userDetailsViewModel.onSurnameChange(it) },
                            readOnly = readOnly,
                            currentText = surname,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledTextColor = Color.White,
                                disabledBorderColor = Color.White,
                                disabledLabelColor = Color.White,
                                disabledLeadingIconColor = Color.White
                            )
                        )
                        UserInfoBox(
                            labelText = "Phone number",
                            leadingIcon = Icons.Filled.Phone,
                            onValueChange = { userDetailsViewModel.onPhoneNumberChange(it) },
                            readOnly = readOnly,
                            currentText = phoneNumber,
                            isNumber = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledTextColor = Color.White,
                                disabledBorderColor = Color.White,
                                disabledLabelColor = Color.White,
                                disabledLeadingIconColor = Color.White
                            )
                        )
                        DateTextField(
                            labelText = "Select birthdate",
                            onValueChange = { userDetailsViewModel.onBirthDateSelect(it) },
                            currentText = birthdate,
                            readOnly = readOnly,
                            leadingIcon = Icons.Filled.DateRange,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledTextColor = Color.White,
                                disabledBorderColor = Color.White,
                                disabledLabelColor = Color.White,
                                disabledLeadingIconColor = Color.White
                            )

                        )
                    }
                    Column(Modifier.weight(0.3f), verticalArrangement = Arrangement.SpaceEvenly) {
                        if (saveLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(25.dp))
                        } else {
                            Button(
                                onClick = { saveProfile() },
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .wrapContentSize()
                            ) {
                                if (readOnly) Text("EDIT PROFILE") else Text("SAVE")
                            }
                        }
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .wrapContentSize()
                        ) {
                            Text("CHANGE PASSWORD")
                        }
                    }

                }
            }
        }
    }
}