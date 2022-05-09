package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.gymappandroid.R
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.ui.commons.DotsIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalCoilApi::class, ExperimentalPagerApi::class)
@Composable
fun ProductDetailsContent(shopViewModel: ShopViewModel) {
    val pagerState = rememberPagerState()
    val selectedProduct by shopViewModel.selectedProduct.collectAsState(Product())

    if (selectedProduct == Product()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
    } else {
        val imageCount = selectedProduct.image.size
        var currentImage = selectedProduct.image.first()

        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            HorizontalPager(count = imageCount, state = pagerState) { page ->
                Box(modifier = Modifier.wrapContentSize()) {
                    Image(
                        painter = rememberImagePainter(
                            data = currentImage,
                            builder = { placeholder(R.drawable.logo) }),
                        contentDescription = "product image",
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(BottomCenter)
                    ) {
                        if (imageCount > 1) {
                            DotsIndicator(
                                totalDots = 3,
                                selectedIndex = page,
                                selectedColor = Color.White,
                                unSelectedColor = Color.Gray
                            )
                        }
                    }
                }
                currentImage = when (page) {
                    1 -> selectedProduct.image[1]
                    2 -> selectedProduct.image[2]
                    else -> selectedProduct.image[0]
                }
            }
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(
                    text = selectedProduct.model,
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
                    text = selectedProduct.description,
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
                        items(selectedProduct.availableSizes) {
                            SizeOptionUI(it)
                        }
                    }
                    Text(
                        "${selectedProduct.price} \n EUR",
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
                            .padding(5.dp)
                            .clickable { },
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
}

