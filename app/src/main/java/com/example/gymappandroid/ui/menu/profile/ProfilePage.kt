package com.example.gymappandroid.ui.menu.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.example.gymappandroid.data.models.User
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
    val readOnly by remember { mutableStateOf(true) }
    val user by userDetailsViewModel.currentUser.observeAsState(initial = User())
    val firestoreResponse by userDetailsViewModel.firestoreStatus.observeAsState("")
    val userSession = dataStore.getUserSession.collectAsState(initial = "")

    fun initScreen() {
        if (userSession.value != "") {
            coroutineScope.launch {
                userDetailsViewModel.getUserProfile(userSession.value!!)
            }
            if (firestoreResponse != "") {
                Toast.makeText(context, firestoreResponse, Toast.LENGTH_SHORT).show()
            }
        }
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
                        onValueChange = { },
                        readOnly = readOnly,
                        currentText = user.name,
                    )
                    UserInfoBox(
                        labelText = "Surname",
                        leadingIcon = Icons.Filled.Person,
                        onValueChange = { },
                        readOnly = readOnly,
                        currentText = user.surname,
                    )
                    UserInfoBox(
                        labelText = "Phone number",
                        leadingIcon = Icons.Filled.Phone,
                        onValueChange = { },
                        readOnly = readOnly,
                        currentText = user.phoneNumber,
                        isNumber = true
                    )
                    DateTextField(
                        labelText = "Select birthdate",
                        onValueChange = { },
                        currentText = user.birthDate,
                        readOnly = readOnly,
                        leadingIcon = Icons.Filled.DateRange
                    )
                }
                Column(Modifier.weight(0.3f), verticalArrangement = Arrangement.SpaceEvenly) {
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .wrapContentSize()
                    ) {
                        Text("EDIT PROFILE")
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