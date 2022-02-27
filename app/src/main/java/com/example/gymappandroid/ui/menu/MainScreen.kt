package com.example.gymappandroid.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gymappandroid.R

@Composable
fun MainScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
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