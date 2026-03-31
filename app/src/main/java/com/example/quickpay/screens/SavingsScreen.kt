package com.example.quickpay.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.AppBar
import com.example.quickpay.ui.theme.ScreenBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SavingsScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {

        AppBar(
            title = "",
            navigator = navigator,
            showMenu = true
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
                top = 4.dp
            )
        ) {

            item { CashBoxCard() }

            item { SectionTitle("Flexible Savings") }
            item { FlexibleSavingsRow() }

            item { SectionTitle("Smart Savings") }
            item { SmartSavingsGrid() }

            item { SectionTitle("Top Mutual Funds") }
            item { MutualFundsList() }
        }
    }
}

@Composable
fun CashBoxCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7D9)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Your available balance with interest",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "20.00%",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )

            Text(
                "Maximum Annual Yield",
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7E57C2)
                )
            ) {
                Text("Save", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Transfer & Pay", fontWeight = FontWeight.Medium, fontSize = 18.sp)
                    Text("Pay like your QuickPay Balance", fontSize = 13.sp)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("24/7 Access", fontWeight = FontWeight.Medium, fontSize = 18.sp)
                    Text("Withdraw Anytime", fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        modifier = Modifier.padding(vertical = 12.dp)
    )
}

@Composable
fun FlexibleSavingsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        FlexibleCard(
            title = "CashBox",
            subtitle = "Available balance earns daily interest",
            amount = "₦2.85 >",
            modifier = Modifier.fillMaxWidth(0.5f)
        )

        FlexibleCard(
            title = "SmartEarn",
            subtitle = "17.18% p.a 24/7 access",
            action = "Save Now",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun FlexibleCard(
    title: String,
    subtitle: String,
    amount: String? = null,
    action: String? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)

            Text(subtitle, fontSize = 15.sp)

            Spacer(modifier = Modifier.height(8.dp))

            amount?.let {
                Text(it, fontWeight = FontWeight.Bold)
            }

            action?.let {
                Text(
                    it,
                    color = Color(0xFF7E57C2),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun SmartSavingsGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SmartCard(
                "Target Savings",
                "Save daily",
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            SmartCard(
                "Fixed Savings",
                "Lock funds",
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SmartCard(
                "Spend & Save",
                "Auto save",
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            SmartCard(
                "SafeBox",
                "Withdraw later",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SmartCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)

            Text(subtitle, fontSize = 15.sp)

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                "Save Now",
                color = Color(0xFF7E57C2),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun MutualFundsList() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        FundItem("ARM MONEY MARKET FUND", "+17.18%", "1")
        FundItem("ARM ETHICAL FUND", "+8.22%", "2")
        FundItem("ARM FIXED INCOME FUND", "+10%", "3")
    }
}

@Composable
fun FundItem(
    name: String,
    returnRate: String,
    rank: String
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text("$rank  $name", fontWeight = FontWeight.Medium, fontSize = 18.sp)
                Text("$returnRate 1Y Return", color = Color(0xFF2E7D32), fontSize = 15.sp)
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7E57C2)
                )
            ) {
                Text("Invest", fontSize = 15.sp)
            }
        }
    }
}