package com.example.gymappandroid.ui.menu.shop.OrderInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymappandroid.R

@Composable
fun OrderInfoScreenContent(orderId: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.dark_blue))
    )
    {
        LaunchedEffect(key1 = orderId) {

        }
        if (orderId.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )
        } else if (orderId != "Error") {
            Text(
                "CONGRATULATIONS \n YOUR ORDER WAS ADDED \n SHOW THIS CODE TO RECEPTION\n $orderId",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Text(
                "THERE WAS A PROBLEM TRY AGAIN LATER",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}