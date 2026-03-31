package com.example.quickpay.sections

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.model.RecentActivity
import kotlinx.coroutines.delay

@Composable
fun RecentActivityAutoSlider() {
    val items = listOf(
        RecentActivity.TrialCash(
            title = "₦505 Trial Cash Await!",
            subtitle = "You've received ₦505 Trial Cash."
        ),
        RecentActivity.MoneyReceived(
            sender = "AKACHUKWU ONYEDIKACHI EJIKE",
            amount = "+ ₦9,020.00",
            dateTime = "Dec 01, 2025, 11:07AM"
        )
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    // Auto slide
    LaunchedEffect(currentIndex) {
        delay(3500)
        currentIndex = (currentIndex + 1) % items.size
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp) // <--- Add a fixed height (adjust as needed)
            .padding(horizontal = 16.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {

        AnimatedContent(
            targetState = items[currentIndex],
            transitionSpec = {
                slideInVertically { it } + fadeIn() togetherWith
                        slideOutVertically { -it } + fadeOut()
            },
            label = "recent-activity"
        ) { item ->

            when (item) {
                is RecentActivity.TrialCash -> TrialCashContent(item)
                is RecentActivity.MoneyReceived -> MoneyReceivedContent(item)
            }
        }
    }
}
@Composable
private fun MoneyReceivedContent(item: RecentActivity.MoneyReceived) {

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Received from ${item.sender}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(8.dp)) // small space between sender and History

            Text(
                text = "History",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF6200EE)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFF6200EE)
            )
        }
        Spacer(Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = item.amount,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = item.dateTime,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
private fun TrialCashContent(item: RecentActivity.TrialCash) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = item.subtitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                )
        }

        Spacer(Modifier.width(10.dp)) // space between text and claim button

        Button(
            onClick = { /* handle click */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE) // purple background
            ),
            shape = RoundedCornerShape(20.dp), // pill shape
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Claim",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}