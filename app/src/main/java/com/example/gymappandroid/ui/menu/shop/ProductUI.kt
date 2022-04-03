package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymappandroid.R

@Composable
fun ProductListItem() {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Profile Image",
                modifier = Modifier.size(150.dp)
            )
            Text(
                "PAPUCI GUCCI, DA-TE MARE ABI",
                fontWeight = Bold,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview
@Composable
fun previewProductItem() {
    ProductListItem()
}