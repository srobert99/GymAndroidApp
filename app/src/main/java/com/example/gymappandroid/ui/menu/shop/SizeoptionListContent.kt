package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SizeOptionList(listOfSizes: List<String>) {
    LazyRow(contentPadding = PaddingValues(20.dp)) {
        items(listOfSizes) {
            SizeOptionUI(it)
        }
    }
}