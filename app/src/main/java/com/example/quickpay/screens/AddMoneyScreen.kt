package com.example.quickpay.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.AppBar
import com.example.quickpay.R
import com.example.quickpay.ui.theme.ScreenBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination
@Composable
fun AddMoneyScreen(
    navigator: DestinationsNavigator
) {

    Scaffold(
        topBar = {
            AppBar(
                title = "Add Money",
                navigator = navigator
            )
        },
        containerColor = ScreenBackground
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFE9EAEC))
                .verticalScroll(rememberScrollState())

        ) {

            Spacer(modifier = Modifier.height(12.dp))

            BankTransferCard()

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 6.dp)
            ) {

                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    thickness = DividerDefaults.Thickness, color = Color.LightGray
                )

                Text(
                    text = "OR",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.Gray,
                    fontSize = 13.sp
                )

                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    thickness = DividerDefaults.Thickness, color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            OptionItem(
                title = "Cash Deposit",
                subtitle = "Fund your account with nearby agents",
                iconRes = R.drawable.letter_n
            )

            OptionItem(
                title = "Top-up with Card/Account",
                subtitle = "Add money from your bank card/account",
                iconRes = R.drawable.atm_card_1
            )

            OptionItem(
                title = "Receive Money",
                subtitle = "Share your account and ask for transfer",
                iconRes = R.drawable.receive_money
            )

            OptionItem(
                title = "USSD",
                subtitle = "Use your other bank’s USSD code",
                iconRes = R.drawable.ussd
            )
            Spacer(modifier = Modifier.height(5.dp))

            PromoBanner()
        }
    }
}

/* -------------------------------------------------------------------------- */
/*                                COMPONENTS                                  */
/* -------------------------------------------------------------------------- */
@Composable
private fun BankTransferCard() {

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Header Row (Icon + Title + Arrow)
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = Color(0xFFEDE7F6),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.govt_payments),
                        contentDescription = null,
                        tint = Color(0xFF6200EE),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        text = "Bank Transfer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "FREE Instant bank funding within 10s",
                        fontSize = 15.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,

                        )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(Modifier, DividerDefaults.Thickness, color = Color(0xFFE5E5E5))

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "QuickPay Account Number",
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "706 690 8262",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {

                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEDE7F6),
                        contentColor = Color(0xFF6200EE)
                    )
                ) {
                    Text("Copy Number", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE)
                    )
                ) {
                    Text(
                        "Share Detail",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }

    }
}

@Composable
private fun OptionItem(
    title: String,
    subtitle: String,
    iconRes: Int
) {

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth()
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 🔹 Soft Circular Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color(0xFFEDE7F6),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )

                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}
@Composable
private fun PromoBanner() {

    val pagerState = rememberPagerState(pageCount = { 2 })

    // Auto Swipe
    LaunchedEffect(Unit) {
        while (true) {
            delay(3500)
            val nextPage = (pagerState.currentPage + 1) % 2
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(170.dp) // give space for indicator inside
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->

            when (page) {
                0 -> ZeroTransferBanner()
                1 -> MoneyArrivedBanner()
            }
        }

        // 🔘 Indicator OVERLAY (inside banner)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(2) { index ->

                val selected = pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(6.dp)
                        .width(if (selected) 18.dp else 6.dp)
                        .background(
                            color = if (selected)
                                Color(0xFFFFC107)
                            else
                                Color.LightGray,
                            shape = RoundedCornerShape(50)
                        )
                )
            }
        }
    }
}
@Composable
private fun ZeroTransferBanner() {

    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF8E2DE2),
            Color(0xFF5E00D6),
            Color(0xFF3A00B7)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = gradient,
                shape = RoundedCornerShape(22.dp)
            )
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {

        //  Your Drawable Image (Bent + Close to Text)
        Icon(
            painter = painterResource(id = R.drawable.zerotransfer_fees),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.TopEnd)
                .offset(x = (-15).dp, y = 7.dp)
                .rotate(-10f)
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 65.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = buildAnnotatedString {

                        withStyle(
                            SpanStyle(
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold
                            )
                        ) { append("ZER") }

                        withStyle(
                            SpanStyle(
                                color = Color(0xFFFFC107),
                                fontWeight = FontWeight.ExtraBold
                            )
                        ) { append("O") }

                        append(" ")

                        withStyle(
                            SpanStyle(
                                color = Color(0xFFFFC107),
                                fontWeight = FontWeight.ExtraBold
                            )
                        ) { append("Transfer Fees") }
                    },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Unlimited Free Transfers",
                    color = Color(0xFFFFC107),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                )
            }

            Button(
                onClick = { },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC107)
                ),
                modifier = Modifier.height(36.dp)
            ) {
                Text(
                    text = "Go Now",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun MoneyArrivedBanner() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFF8E2DE2),
                shape = RoundedCornerShape(22.dp)
            )
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {

        /* ----------- RIGHT SIDE ICON ----------- */

        Icon(
            painter = painterResource(id = R.drawable.telemedicine_4),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.TopEnd)
                .offset(x = (10).dp, y = 7.dp) // pull closer to text
                .rotate(-8f)
        )

        /* ----------- LEFT CONTENT ----------- */

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 70.dp), //
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = "₦120,000 Has Arrived",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color(0xFFFFC107)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "Use for Medicine",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .background(
                                Color(0xFF5E00D6),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "Expires Soon",
                            fontSize = 13.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Button(
                onClick = { },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC107)
                ),
                modifier = Modifier.height(36.dp)
            ) {
                Text(
                    text = "Claim Now",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}