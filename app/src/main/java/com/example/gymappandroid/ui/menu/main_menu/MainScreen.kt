package com.example.gymappandroid.ui.menu.main_menu

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gymappandroid.R
import com.example.gymappandroid.data.models.User
import com.example.gymappandroid.utils.DataStore
import kotlinx.coroutines.launch

@Composable
fun MainScreen(mainScreenViewModel: MainScreenViewModel) {
    val context = LocalContext.current
    val dataStore = DataStore(context)
    val coroutineScope = rememberCoroutineScope()
    val user by mainScreenViewModel.currentUser.observeAsState(initial = User())
    val firestoreResponse by mainScreenViewModel.firestoreStatus.observeAsState("")
    val userSession = dataStore.getUserSession.collectAsState(initial = "")

    fun initScreen() {
        if (userSession.value != "") {
            coroutineScope.launch {
                mainScreenViewModel.getUserData(userSession.value)
            }
            if (firestoreResponse != "") {
                Toast.makeText(context, firestoreResponse, Toast.LENGTH_SHORT).show()
            }
        }
    }

    initScreen()

    Column(Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(id = R.color.black),
                            colorResource(id = R.color.top_bar_color)
                        )
                    )
                )
                .weight(0.05f)
                .fillMaxSize()
        ) {
            Box(Modifier.fillMaxSize()) {
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        user.name + " " + user.surname,
                        color = colorResource(id = R.color.white),
                    )
                }
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(user.balance.toString(), color = colorResource(id = R.color.white))
                    Image(
                        painter = painterResource(R.drawable.shopping_cart),
                        contentDescription = "shopping cart icon",
                    )

                }

            }

        }
        Surface(
            modifier = Modifier
                .weight(0.95f)
                .fillMaxSize()
        ) {
            Row(Modifier.fillMaxSize()) {
                //Set top margin for the column with the buttons
                Divider(
                    Modifier
                        .weight(0.1f)
                        .fillMaxSize(),
                    color = colorResource(id = R.color.dark_blue)
                )
                Column(
                    Modifier
                        .weight(0.8f)
                        .fillMaxSize()
                ) {
                    Divider(
                        Modifier
                            .weight(0.2f)
                            .fillMaxSize(),
                        color = colorResource(id = R.color.dark_blue)
                    )
                    Card(
                        backgroundColor = colorResource(id = R.color.teal_200),
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.shopping_cart),
                                contentDescription = "shopping cart icon",
                                Modifier
                                    .size(dimensionResource(id = R.dimen.logo_size))
                                    .padding(end = 10.dp)
                            )
                            Text(
                                "SHOP",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.dark_blue)
                            )
                        }
                    }
                    Divider(
                        Modifier
                            .weight(0.1f)
                            .fillMaxSize(),
                        color = colorResource(id = R.color.dark_blue)
                    )
                    Card(
                        backgroundColor = colorResource(id = R.color.teal_200),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.person_vector),
                                contentDescription = "person vector image",
                                Modifier
                                    .size(dimensionResource(id = R.dimen.logo_size))
                            )
                            Text(
                                "PROFILE",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.dark_blue)
                            )
                        }
                    }
                    Divider(
                        Modifier
                            .weight(0.2f)
                            .fillMaxSize(),
                        color = colorResource(id = R.color.dark_blue)
                    )
                }
                //Set bottom margin for the column with the buttons
                Divider(
                    Modifier
                        .weight(0.1f)
                        .fillMaxSize(),
                    color = colorResource(id = R.color.dark_blue)
                )
            }
        }
    }
}