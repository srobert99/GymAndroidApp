package com.example.gymappandroid.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gymappandroid.R
import com.example.gymappandroid.ui.account.user_details.UserDetailsViewModel

@Composable
fun TopMenuBar(detailsViewModel: UserDetailsViewModel) {
    val name by detailsViewModel.name.observeAsState("")
    val surname by detailsViewModel.surname.observeAsState("")
    val balance by detailsViewModel.coins.observeAsState("")

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
                    "$name $surname",
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
                Text(balance, color = colorResource(id = R.color.white))
                Image(
                    painter = painterResource(R.drawable.shopping_cart),
                    contentDescription = "shopping cart icon",
                )

            }

        }

    }
}

