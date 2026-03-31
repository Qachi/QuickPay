package com.example.quickpay.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.menu.TopDownStatusMenu
import com.example.quickpay.AppBar
import com.example.quickpay.R
import com.example.quickpay.bottomsheet.TransactionFilterSheet
import com.example.quickpay.data.TransactionDummy
import com.example.quickpay.model.Transaction
import com.example.quickpay.model.TransactionType
import com.example.quickpay.util.calculateTotals
import com.example.quickpay.util.extractMonth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun TransactionItem(transaction: Transaction) {

    val amountColor =
        if (transaction.type == TransactionType.RECEIVE)
            Color(0xFF4CAF50)
        else
            Color.Red

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    if (transaction.type == TransactionType.RECEIVE)
                        Color(0xFFE3F2FD)
                    else
                        Color(0xFFEDE7F6),
                    androidx.compose.foundation.shape.CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = androidx.compose.ui.res.painterResource(
                    id =
                        if (transaction.type == TransactionType.RECEIVE)
                            R.drawable.money_received_
                        else
                            R.drawable.money_sent_
                ),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = transaction.title,
                fontSize = 17.sp,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = transaction.date,
                fontSize = 15.sp,
                color = Color.Gray
            )
        }

        Text(
            text = transaction.amount,
            fontSize = 20.sp,
            color = amountColor
        )
    }
}

@Composable
fun MonthFilter(
    selectedMonth: String,
    months: List<String>,
    incoming: String,
    outgoing: String,
    onMonthSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { expanded = true }
        ) {

            Text(
                text = selectedMonth,
                fontSize = 25.sp
            )

            Icon(
                imageVector = androidx.compose.material.icons.Icons.Outlined.CalendarMonth,
                contentDescription = "Select Month",
                tint = Color(0xFF6200EE),
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 6.dp)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                months.forEach { month ->

                    DropdownMenuItem(
                        text = { Text(month) },
                        onClick = {
                            expanded = false
                            onMonthSelected(month)
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("In $incoming", fontSize = 16.sp)

            Spacer(modifier = Modifier.width(12.dp))

            Text("Out $outgoing", fontSize = 16.sp)
        }
    }
}

@Destination
@Composable
fun TransactionHistoryScreen(
    navigator: DestinationsNavigator
) {

    val transactions = TransactionDummy.transactions

    var showFilters by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf("All Status") }
    var statusExpanded by remember { mutableStateOf(false) }

    val months = transactions.map { extractMonth(it.date) }.distinct()
    var selectedMonth by remember { mutableStateOf(months.first()) }

    val filteredTransactions = transactions.filter {

        extractMonth(it.date) == selectedMonth &&
                (selectedStatus == "All Status" ||
                        it.status.equals(selectedStatus, true))
    }

    val (incoming, outgoing) = calculateTotals(filteredTransactions)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // MAIN CONTENT //

        Column {

            AppBar(title = "Transaction History", navigator = navigator)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier.clickable { showFilters = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "All Categories",
                        fontSize = 17.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        painter = androidx.compose.ui.res.painterResource(R.drawable.filter_icon),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.Gray
                    )
                }

                Row(
                    modifier = Modifier.clickable {
                        statusExpanded = !statusExpanded
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = selectedStatus,
                        fontSize = 17.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text("⌄", fontSize = 17.sp, color = Color.Gray)
                }
            }

            HorizontalDivider(thickness = 1.dp, color = Color(0xFFF0F0F0))

            MonthFilter(
                selectedMonth = selectedMonth,
                months = months,
                incoming = incoming,
                outgoing = outgoing,
                onMonthSelected = { selectedMonth = it }
            )

            LazyColumn {

                items(filteredTransactions) { transaction ->

                    TransactionItem(transaction)

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0xFFF5F5F5)
                    )
                }
            }
        }

        // DROPDOWN OVERLAY //

        if (statusExpanded) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 160.dp)
            ) {

                TopDownStatusMenu(
                    selectedStatus = selectedStatus,
                    onStatusSelected = {
                        selectedStatus = it
                        statusExpanded = false
                    }
                )
            }
        }
    }

    if (showFilters) {

        TransactionFilterSheet(
            onDismiss = { showFilters = false }
        )
    }
}