package com.example.gymappandroid.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gymappandroid.R
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel

@Composable
fun MainTopAppBar(
    detailsViewModel: UserDetailsViewModel,
) {
    val name by detailsViewModel.name.observeAsState("")
    val surname by detailsViewModel.surname.observeAsState("")
    val balance by detailsViewModel.coins.observeAsState("")

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Profile Image"
                    )
                }
                Text(text = "$name $surname", modifier = Modifier.padding(10.dp))
            }
        },
        backgroundColor = colorResource(id = R.color.dark_blue),
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Coins"
                )
                Text(balance)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Shopping cart"
                    )
                }
            }
        }
    )
}

