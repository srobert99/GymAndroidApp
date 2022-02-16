package com.example.gymappandroid.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.gymappandroid.R

@OptIn(ExperimentalMaterialApi::class)
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
                    backgroundColor = colorResource(id = R.color.black),
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Hello world")
                    }
                }
                Divider(
                    Modifier
                        .weight(0.1f)
                        .fillMaxSize(),
                    color = colorResource(id = R.color.dark_blue)
                )
                Card(
                    backgroundColor = colorResource(id = R.color.black),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Hello world")
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
                    .background(colorResource(id = R.color.dark_blue))
            )
        }
    }
}