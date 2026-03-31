package com.example.quickpay.carouselAndIndicator

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.R
import com.example.quickpay.model.PromoItem
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromoCardCarousel() {

    val items = listOf(
        PromoItem(
            title = "Virtual Card Only ₦199 Today",
            subtitle = "Instant Access Google Play",
            iconRes = R.drawable.virtual_card
        ),
        PromoItem(
            title = "Pay Bills Faster with QuickPay",
            subtitle = "Electricity, TV & Internet",
            iconRes = R.drawable.utilities
        ),
        PromoItem(
            title = "Enjoy Cashback Rewards",
            subtitle = "Earn while you pay",
            iconRes = R.drawable.smartphone
        )
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    // Auto change content
    LaunchedEffect(currentIndex) {
        delay(3000)
        currentIndex = (currentIndex + 1) % items.size
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(
                top = 17.dp,
                bottom = 20.dp
            )
    ) {
        // CONTENT SWITCH (not sliding card)
        AnimatedContent(
            targetState = items[currentIndex],
            transitionSpec = {
                slideInHorizontally { it } + fadeIn() togetherWith
                        slideOutHorizontally { -it } + fadeOut()
            },
            label = "promo"
        ) { item ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(item.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(46.dp),
                    tint = Color.Unspecified
                )

                Spacer(Modifier.width(12.dp))

                Column {
                    Text(
                        text = item.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                    Text(
                        text = item.subtitle,
                        fontSize = 15.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                    )
                }
                Spacer(Modifier.weight(1f))
                ApplyChip()
            }
        }
        Spacer(Modifier.height(10.dp))
        PromoIndicator(
            count = items.size,
            current = currentIndex
        )
    }
}
@Composable
fun PromoIndicator(
    count: Int,
    current: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .height(6.dp)
                    .width(if (index == current) 10.dp else 6.dp)
                    .background(
                        if (index == current)
                            Color(0xFF6200EE)
                        else
                            Color(0xFFE0D7FF),
                        RoundedCornerShape(50)
                    )
            )
        }
    }
}
@Composable
fun ApplyChip() {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color.White,
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFF6B4EFF) // purple outline
        )
    ) {
        Text(
            text = "Apply",
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 7.dp
            ),
            color = Color(0xFF6B4EFF),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
