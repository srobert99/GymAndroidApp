package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymappandroid.data.models.SizeOption

@Composable
fun SizeOptionUI(sizeOption: SizeOption, shopViewModel: ShopViewModel) {
    val selectedSize by shopViewModel.selectedSize.collectAsState("")

    Card(
        modifier = Modifier
            .clickable { shopViewModel.selectSize(sizeOption.sizeName) }
            .size(75.dp)
            .padding(10.dp),
        backgroundColor = if (sizeOption.sizeName == selectedSize)
            Color.Black else Color.Gray
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = sizeOption.sizeName,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}