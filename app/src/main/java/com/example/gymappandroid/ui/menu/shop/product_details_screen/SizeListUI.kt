package com.example.gymappandroid.ui.menu.shop.product_details_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.gymappandroid.R

@Composable
fun SizeOptionUI(
    specification: String,
    currentSelectedSpecification: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        backgroundColor = if (specification == currentSelectedSpecification)
            colorResource(id = R.color.teal_200) else Color.Gray
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = specification,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}