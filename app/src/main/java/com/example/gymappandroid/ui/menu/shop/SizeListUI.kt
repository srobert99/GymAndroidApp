package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun SizeOptionUI(size: String) {
    Card(
        modifier = Modifier
            .clickable { }
            .size(75.dp)
            .padding(10.dp),
        backgroundColor = colorResource(id = R.color.black)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = size,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}