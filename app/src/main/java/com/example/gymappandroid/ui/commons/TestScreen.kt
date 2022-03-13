//package com.example.gymappandroid.ui.commons
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import com.example.gymappandroid.R
//import com.example.gymappandroid.data.models.User
//import com.example.gymappandroid.ui.account.user_details.UserDetailsViewModel
//import com.example.gymappandroid.ui.menu.TopMenuBar
//import com.example.gymappandroid.utils.DataStore
//import kotlinx.coroutines.launch
//
//@Composable
//fun TestScreen(detailsViewModel: UserDetailsViewModel) {
//    val name by detailsViewModel.name.observeAsState(initial = "")
//    val coroutineScope = rememberCoroutineScope()
//    val context = LocalContext.current
//    val dataStore = DataStore(context)
//    val userSession = dataStore.getUserSession.collectAsState(initial = "")
//
//
//    fun getData() {
//        if (detailsViewModel.currentUser.value == User()) {
//            coroutineScope.launch {
//                if (userSession.value != "") {
//                    detailsViewModel.getUserProfile(userSession.value!!)
//                }
//            }
//        }
//    }
//
//    getData()
//
//    val user by detailsViewModel.currentUser.observeAsState(initial = User())
//
//    Column(Modifier.fillMaxSize()) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            Surface(
//                modifier = Modifier
//                    .background(
//                        brush = Brush.verticalGradient(
//                            colors = listOf(
//                                colorResource(id = R.color.black),
//                                colorResource(id = R.color.top_bar_color)
//                            )
//                        )
//                    )
//                    .fillMaxSize()
//                    .weight(0.1f)
//            ) {
//                Box(Modifier.fillMaxSize()) {
//                    Row(
//                        Modifier
//                            .fillMaxSize()
//                            .padding(horizontal = 10.dp),
//                        horizontalArrangement = Arrangement.Start,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(
//                            user.name + " " + user.surname,
//                            color = colorResource(id = R.color.white),
//                        )
//                    }
//                    Row(
//                        Modifier
//                            .fillMaxSize()
//                            .padding(horizontal = 10.dp),
//                        horizontalArrangement = Arrangement.End,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(user.balance.toString(), color = colorResource(id = R.color.white))
//                        Image(
//                            painter = painterResource(R.drawable.shopping_cart),
//                            contentDescription = "shopping cart icon",
//                        )
//
//                    }
//
//                }
//            }
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .weight(0.9f), Arrangement.Center
//            ) {
//                Text("Hello world")
//            }
//        }
//    }
//}