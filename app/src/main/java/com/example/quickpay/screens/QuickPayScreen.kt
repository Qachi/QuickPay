package com.example.quickpay.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.AppBar
import com.example.quickpay.model.TransferData
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun QuickPayScreen(
    navigator: DestinationsNavigator
) {

    var selectedTab by remember { mutableIntStateOf(0) }
    var accountNumber by remember { mutableStateOf("") }

    // SEARCH STATE
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    val tabs = listOf("Recent", "Favorites")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
    ) {

        AppBar(
            title = "Transfer to QuickPay",
            navigator = navigator
        )

        Column(modifier = Modifier.padding(16.dp)) {

            //  TOP CARD
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        "Recipient Account",
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = accountNumber,
                        onValueChange = {
                            if (it.all { c -> c.isDigit() } && it.length <= 10) {
                                accountNumber = it
                            }
                        },
                        placeholder = {
                            Text("Enter 10-digit Account No. or Phone No.")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedIndicatorColor = Color(0xFF6200EE),
                            unfocusedIndicatorColor = Color.LightGray
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { },
                        enabled = accountNumber.length >= 10,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF7A5AF8),
                            disabledContainerColor = Color(0xFFE6E0F8)
                        )
                    ) {
                        Text(
                            "Next",
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TABS + SEARCH
            Row(verticalAlignment = Alignment.CenterVertically) {

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    tabs.forEachIndexed { index, title ->

                        Column(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .clickable { selectedTab = index },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = title,
                                color = if (selectedTab == index)
                                    Color(0xFF6200EE)
                                else
                                    Color.Gray,
                                fontWeight = if (selectedTab == index)
                                    FontWeight.SemiBold
                                else
                                    FontWeight.Normal
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            if (selectedTab == index) {
                                Box(
                                    modifier = Modifier
                                        .height(2.dp)
                                        .width(24.dp)
                                        .background(Color(0xFF6200EE))
                                )
                            }
                        }
                    }
                }

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = if (isSearchActive) Color(0xFF6200EE) else Color.Gray,
                    modifier = Modifier.clickable {
                        isSearchActive = !isSearchActive
                        if (!isSearchActive) searchQuery = ""
                    }
                )
            }

            // SEARCH FIELD
            if (isSearchActive) {
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search name or account") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                searchQuery = ""
                                isSearchActive = false
                            }
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // FILTER
            val baseList = if (selectedTab == 0) {
                getRecentQuickPayList()
            } else {
                getFavoriteQuickPayList()
            }

            val list = if (searchQuery.isBlank()) {
                baseList
            } else {
                baseList.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                            (it.subtitle?.contains(searchQuery, ignoreCase = true) == true)
                }
            }

            LazyColumn {
                items(list) {
                    QuickPayItem(it)
                }
            }
        }
    }
}
@Composable
fun QuickPayItem(data: TransferData) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(data.color),
                contentAlignment = Alignment.Center
            ) {
                val initials = data.title
                    .split(" ")
                    .take(2)
                    .map { it.first() }
                    .joinToString("")

                Text(
                    text = initials,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        data.title,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )

                    if (data.avatar == "Agent") {
                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            "Agent",
                            color = Color(0xFF2ECC71),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .background(
                                    Color(0x1A2ECC71),
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(
                    data.subtitle ?: "",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                // Show date ONLY if it exists
                if (!data.date.isNullOrEmpty()) {
                    Text(
                        data.date,
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }
            }
        }

        // Optional "Set Up" row (first item style)
        if (data.title.contains("MICHEAL")) {
            Spacer(modifier = Modifier.height(6.dp))

            Row {
                Text(
                    "Add to Favorite for a seamless transfer",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    "Set Up >",
                    color = Color(0xFF6200EE),
                    fontSize = 13.sp
                )
            }
        }
    }
}

fun getRecentQuickPayList(): List<TransferData> {
    return listOf(
        TransferData("AKACHUKWU ONYEDIKACHI","7066908262 OPay","Yesterday","OPay",Color(0xFF4CAF50)),
        TransferData("POS Transfer - BOSEDE ADERONKE","5818115273 Moniepoint","Last transfer on Mar 14, 2026","M",Color(0xFF0000FF)),
        TransferData("ONYEDIKACHI AKACHUKWU","7066908262 GT BANK","Yesterday","GTB",Color(0xFFFF9800)),
        TransferData("CHIKE AKACHUKWU","5818115273 Moniepoint","Last transfer on Mar 14, 2026","M",Color(0xFF0000FF)),
        TransferData("ONYEDIKACHI AKACHUKWU","7066908262 OPay","Last transfer on Mar 19, 2026","OPay",Color(0xFF4CAF50))
    )
}

fun getFavoriteQuickPayList(): List<TransferData> {
    return listOf(
        TransferData("AKACHUKWU ONYEDIKACHI", "7066908262 OPay", null, "OPay", Color(0xFF4CAF50)),
        TransferData("ONYEDIKACHI AKACHUKWU", "7066908262 GT Bank", null, "GTB", Color(0xFFFF9800)),
        TransferData("JOY AKACHUKWU", "5818115273 Moniepoint", null, "M", Color(0xFF0000FF)),
        TransferData(
            "CHIKE AKACHUKWU",
            "5818115273 Moniepoint",
            "Last transfer on Mar 14, 2026",
            "M",
            Color(0xFF0000FF)
        ),
        TransferData("ONYEDIKACHI AKACHUKWU", "7066908262 GT BANK", null, "GTB", Color(0xFFFF9800))
    )
}