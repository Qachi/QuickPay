package com.example.quickpay.bottomsheet

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.components.FilterChip

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionFilterSheet(
    onDismiss: () -> Unit
) {

    val screenHeight: Dp = LocalConfiguration.current.screenHeightDp.dp

    var moneyFlow by remember { mutableStateOf("All") }
    var app by remember { mutableStateOf("All") }
    var paymentType by remember { mutableStateOf("All Categories") }
    var billCategory by remember { mutableStateOf("") }
    var otherType by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = screenHeight * 0.85f) // 85% of screen height
                .padding(20.dp)
        ) {
            item {
                // MONEY FLOW
                Text(
                    "Money Flow",
                    fontSize = 19.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )
                Spacer(Modifier.height(10.dp))
                Row {
                    FilterChip("All", moneyFlow == "All") { moneyFlow = "All" }
                    FilterChip("Money in", moneyFlow == "Money in") { moneyFlow = "Money in" }
                    FilterChip("Money Out", moneyFlow == "Money Out") { moneyFlow = "Money Out" }
                }

                Spacer(Modifier.height(20.dp))

                // APP
                Text(
                    "App",
                    fontSize = 19.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )
                Spacer(Modifier.height(10.dp))
                Row {
                    FilterChip("All", app == "All") { app = "All" }
                    FilterChip("QuickPay", app == "QuickPay") { app = "QuickPay" }
                    FilterChip("QuickPay Business", app == "QuickPay Business") {
                        app = "QuickPay Business"
                    }
                }

                Spacer(Modifier.height(20.dp))

                // PAYMENT TYPE
                Text(
                    "Payment Type",
                    fontSize = 19.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )
                Spacer(Modifier.height(4.dp))
                Text("Quick Filter", fontSize = 14.sp, color = Color.Gray)
                Spacer(Modifier.height(10.dp))
                FlowRow {
                    val quickFilters = listOf(
                        "All Categories", "Money Transfer", "Add Money", "Refund",
                        "Interbank Transfer", "Withdraw"
                    )
                    quickFilters.forEach {
                        FilterChip(text = it, selected = paymentType == it) { paymentType = it }
                    }
                }

                Spacer(Modifier.height(20.dp))

                // BILL CATEGORY
                Text("Bill Category", fontSize = 14.sp, color = Color.Gray)
                Spacer(Modifier.height(10.dp))
                FlowRow {
                    val categories = listOf(
                        "Top up Airtime",
                        "Buy Data Bundle",
                        "Betting",
                        "TV",
                        "Electricity",
                        "Water",
                        "Internet",
                        "Dues & Service Charge",
                        "Lending Service",
                        "Associations & Societies",
                        "Event ticket",
                        "Transportation & Tolls",
                        "Government Payments",
                        "Invoice Payments",
                        "Travel & Hotel",
                        "Financial Institution",
                        "BankOne MFBs",
                        "Business Payments"
                    )
                    categories.forEach {
                        FilterChip(text = it, selected = billCategory == it) { billCategory = it }
                    }
                }

                Spacer(Modifier.height(20.dp))

                // OTHER
                Text("Other", fontSize = 14.sp, color = Color.Gray)
                Spacer(Modifier.height(10.dp))
                FlowRow {
                    val others = listOf(
                        "CashBox",
                        "Activity",
                        "Cash In",
                        "Cash Out",
                        "Merchants",
                        "Commission",
                        "Auto-deduct",
                        "Repay loan",
                        "Lucky Money",
                        "Disbursement",
                        "Group Discount",
                        "Shopana",
                        "Online Shopping",
                        "Event ticket",
                        "Commerce Retail Trade",
                        "Fixed Savings",
                        "Fixed Saving Payback",
                        "QR Code"
                    )
                    others.forEach {
                        FilterChip(text = it, selected = otherType == it) { otherType = it }
                    }
                }

                Spacer(Modifier.height(30.dp))

                // BUTTONS
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8E3F7))
                    ) { Text("Cancel", color = Color(0xFF6B4EFF)) }

                    Spacer(Modifier.width(12.dp))

                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6B4EFF)
                        )
                    ) { Text("Confirm") }
                }

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}