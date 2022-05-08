package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.gymappandroid.R
import com.example.gymappandroid.ui.commons.DotsIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalCoilApi::class, ExperimentalPagerApi::class)
@Composable
fun ProductDetailsContent() {
    val sizesList = listOf("M", "S", "XL", "XS")
    val pagerState = rememberPagerState()
    var request by remember { mutableStateOf("https://images.dog.ceo/breeds/terrier-norfolk/n02094114_1860.jpg") }

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        HorizontalPager(count = 3, state = pagerState) { page ->
            Box(modifier = Modifier.wrapContentSize()) {
                Image(
                    painter = rememberImagePainter(request),
                    contentDescription = "shop category 1",
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
                request = when (page) {
                    1 -> "https://images.dog.ceo/breeds/shihtzu/n02086240_1690.jpg"
                    2 -> "https://images.dog.ceo/breeds/kuvasz/n02104029_31.jpg"
                    else -> "https://images.dog.ceo/breeds/terrier-norfolk/n02094114_1860.jpg"
                }
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(BottomCenter)
                ) {
                    DotsIndicator(
                        totalDots = 3,
                        selectedIndex = page,
                        selectedColor = Color.White,
                        unSelectedColor = Color.Gray
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = "ADIDAS XML",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 10.dp)
                    .background(Color.Black),
            )
        }
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .wrapContentSize()
            ) {
                Text(
                    text = "DESCRIPTION",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp),
                )
            }
            Text(
                text = stringResource(id = R.string.dummy_text),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .padding(bottom = 30.dp),
                fontFamily = FontFamily.Monospace,
                fontSize = 15.sp
            )
            Text(
                text = "REVIEWS",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                modifier = Modifier
                    .background(Color.Black)
                    .padding(5.dp)
            )
            Text(
                "No reviews yet",
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 30.dp)
                    .align(CenterHorizontally),
                color = Color.Gray,
                fontSize = 12.sp
            )
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .wrapContentSize()
            ) {
                Text(
                    text = "AVAILABLE SIZES",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 5.dp, bottom = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyRow(
                    contentPadding = PaddingValues(end = 20.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 20.dp)
                ) {
                    items(sizesList) {
                        SizeOptionUI(it)
                    }
                }
                Text(
                    "240\nEUR",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace
                )
            }

            Card(
                modifier = Modifier
                    .clickable { }
                    .align(CenterHorizontally)
                    .padding(bottom = 10.dp)
                    .wrapContentSize(),
                backgroundColor = Color.Black,
                contentColor = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "ADD TO CART ",
                        fontSize = 15.sp,
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "shopping cart",
                        Modifier
                            .size(25.dp)
                    )
                }

            }

        }
    }
}

