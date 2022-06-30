package com.example.gymappandroid.ui.menu.shop.review_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymappandroid.R
import com.example.gymappandroid.data.models.Review
import com.example.gymappandroid.ui.menu.shop.ShopViewModel

@Composable
fun AddReviewScreenContent(
    shopViewModel: ShopViewModel,
    name: String,
    surname: String,
    navController: NavController
) {
    var stars by remember {
        mutableStateOf(0)
    }

    var reviewComment by remember {
        mutableStateOf("Add your review here")
    }

    val onValueChange: (newText: String) -> Unit = {
        reviewComment = it
    }

    val context = LocalContext.current

    val onConfirmPressed: () -> Unit = {
        if (stars != 0 && reviewComment != "Add your review here" && reviewComment.isNotEmpty()) {
            val review = Review(
                comment = reviewComment,
                stars = stars,
                product_id = shopViewModel.currentSelectedProduct.value.id,
                username = "$name $surname"
            )
            shopViewModel.addReview(review)
            navController.popBackStack()
        } else {
            Toast.makeText(context, "Invalid data", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.dark_blue))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(colorResource(id = R.color.dark_blue)),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = "SELECT STARS",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                for (number in 1..5) {
                    if (number <= stars) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            null,
                            modifier = Modifier
                                .clickable {
                                    stars = number
                                }
                                .size(50.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.StarHalf,
                            null,
                            modifier = Modifier
                                .clickable {
                                    stars = number
                                }
                                .size(50.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Divider(color = Color.Black)
            Spacer(modifier = Modifier.size(10.dp))

            TextField(
                value = reviewComment,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxSize()
            )
        }
        Button(onClick = { onConfirmPressed() }, modifier = Modifier.align(BottomCenter)) {
            Text("DONE", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

