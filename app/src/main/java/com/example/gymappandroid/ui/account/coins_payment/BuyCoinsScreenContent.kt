package com.example.gymappandroid.ui.account.coins_payment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymappandroid.R
import com.example.gymappandroid.data.models.CoinOffer
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel

@Composable
fun BuyCoinsScreenContent(
    uid: String,
    userDetailsViewModel: UserDetailsViewModel,
    buyCoinsMethod: (String, Int) -> Unit
) {
    val listOfOffers =
        listOf(
            CoinOffer(100, 50),
            CoinOffer(200, 100),
            CoinOffer(350, 110),
            CoinOffer(500, 150),
            CoinOffer(1000, 250)
        )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(id = R.color.dark_blue)
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (offer in listOfOffers) {
            OfferItemUI(offer = offer, uid = uid, buyCoinsMethod)
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
private fun OfferItemUI(
    offer: CoinOffer,
    uid: String,
    buyCoinsMethod: (uid: String, amount: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .clickable { buyCoinsMethod(uid, offer.amount) },
        backgroundColor = colorResource(id = R.color.teal_200),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                offer.icon,
                null,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = offer.amount.toString(), fontWeight = FontWeight.Bold,
                color = Color.White, fontSize = 30.sp
            )
        }
    }
}