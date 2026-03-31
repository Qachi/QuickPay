package com.example.quickpay.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.AppBar
import com.example.quickpay.R
import com.example.quickpay.ui.theme.ScreenBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CashBoxAutoSaveScreen(
    navigator: DestinationsNavigator
) {

    var isChecked by remember { mutableStateOf(false) }

    val buttonColor =
        if (isChecked) Color(0xFF6A3DE8) else Color(0xFFD9D9D9)

    val buttonTextColor =
        if (isChecked) Color.White else Color.DarkGray

    Scaffold(
        containerColor = ScreenBackground,
        topBar = {
            AppBar(
                title = "CashBox Auto Save",
                navigator = navigator
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = "Start Earning from Your\nAvailable Balance",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 35.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Turn on Auto Save, money in your QuickPay Balance will be automatically moved to CashBox to earn daily.",
                fontSize = 15.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ---------------- Comparison Card ----------------

            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            "QuickPay Balance", fontSize = 15.sp,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "0%",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text("Annual Yield", fontSize = 12.sp)

                        Spacer(modifier = Modifier.height(16.dp))

                        Text("✖ No Earning", color = Color.Gray)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("✔ Free Withdrawals")
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("✔ Make Payments")
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFF2E2B2B))
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(color = Color.White, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cashbox),
                                contentDescription = "null",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Text(
                            "CashBox",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color(0xFF6A3DE8),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally // Centers text inside the Column
                            ) {

                                Text(
                                    "UP TO 20%",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    "Annual Yield",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text("✔ Earn Interest Daily", color = Color.Yellow)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("✔ Free Withdrawals", color = Color.White)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("✔ Make Payments", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ---------------- Earnings Highlight ----------------

            Card(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = buildAnnotatedString {

                        append("Earn ")

                        withStyle(
                            style = SpanStyle(color = Color(0xFF6A3DE8))
                        ) {
                            append("₦41.6")
                        }

                        append(" daily just by depositing ")

                        withStyle(
                            style = SpanStyle(color = Color(0xFF6A3DE8))
                        ) {
                            append("₦100,000")
                        }

                        append(" in CashBox!")
                    },
                    modifier = Modifier.padding(12.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ---------------- Accept Button ----------------

            Button(
                onClick = { },
                enabled = isChecked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {

                Text(
                    text = "Accept & Turn on Auto Save",
                    fontSize = 16.sp,
                    color = buttonTextColor,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ---------------- Checkbox ----------------

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF6A3DE8),
                        uncheckedColor = Color.Gray,
                        checkmarkColor = Color.White
                    )
                )

                Text(
                    text = buildAnnotatedString {
                        append("I confirm and accept ")
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF6A3DE8), // Matches your brand purple
                                textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                            )
                        ) {
                            append("Automatic Saving Service Agreement")
                        }
                    },
                    fontSize = 12.sp, // Reduced slightly to ensure it fits on one line
                    maxLines = 1,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ---------------- What Can You Get ----------------

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        "What can you get?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("1. Stress-Free earning on every Naira")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("2. Withdraw from CashBox anytime")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("3. Spend money like your QuickPay balance")

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = buildAnnotatedString {
                            append("Savings services are provided by ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black
                                )
                            ) {
                                append("Blooms MFB")
                            }
                            append(" Ltd")
                        },
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}