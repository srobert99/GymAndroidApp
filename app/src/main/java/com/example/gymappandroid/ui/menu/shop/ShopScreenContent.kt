package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymappandroid.data.models.Product

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShopScreenContent(shopViewModel:ShopViewModel,navController: NavController) {
    Surface() {
        LazyVerticalGrid(
            contentPadding = PaddingValues(16.dp),
            cells = GridCells.Adaptive(150.dp)
        ) {
            items(
                items = shopViewModel.shopItems,
                itemContent = {
                    ProductListItem()
                }
            )
        }
    }
}