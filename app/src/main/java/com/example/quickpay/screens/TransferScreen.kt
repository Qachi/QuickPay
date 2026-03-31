package com.example.quickpay.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.AppBar
import com.example.quickpay.R
import com.example.quickpay.model.TransferData
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TransferScreen(
    navigator: DestinationsNavigator
) {

    var selectedTab by remember { mutableIntStateOf(0) }
    var selectedHistoryTab by remember { mutableIntStateOf(0) }

    val tabs = listOf("To Bank", "To QuickPay")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
    ) {

        AppBar(
            title = "Transfer to Bank",
            navigator = navigator
        )

        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    height = 3.dp,
                    color = Color(0xFF6200EE)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->

                val isSelected = selectedTab == index

                Tab(
                    selected = isSelected,
                    onClick = {
                        selectedTab = index

                        // CHAINED TAB LOGIC
                        if (index == 1) {
                            selectedHistoryTab = 2 // QuickPay Contacts
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (isSelected) Color(0xFF6200EE) else Color.Black
                        )
                    }
                )
            }
        }

        when (selectedTab) {
            0 -> BankTransferContent(
                selectedTab = selectedTab,
                selectedHistoryTab = selectedHistoryTab,
                onHistoryTabChange = { selectedHistoryTab = it }
            )

            1 -> BankTransferContent(
                selectedTab = selectedTab,
                selectedHistoryTab = selectedHistoryTab,
                onHistoryTabChange = { selectedHistoryTab = it }
            )
        }
    }
}

@Composable
fun BankTransferContent(
    selectedTab: Int,
    selectedHistoryTab: Int,
    onHistoryTabChange: (Int) -> Unit
) {

    var accountNumber by remember { mutableStateOf("") }

    val historyTabs = listOf("Recent", "Favorites", "QuickPay Contacts")

    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    val recentList = listOf(
        TransferData("AKACHUKWU ONYEDIKACHI","7066908262 OPay","Yesterday","OPay",Color(0xFF4CAF50)),
        TransferData("POS Transfer - BOSEDE ADERONKE","5818115273 Moniepoint","Last transfer on Mar 14, 2026","M",Color(0xFF0000FF)),
        TransferData("ONYEDIKACHI AKACHUKWU","7066908262 GT BANK","Yesterday","GTB",Color(0xFFFF9800)),
        TransferData("CHIKE AKACHUKWU","5818115273 Moniepoint","Last transfer on Mar 14, 2026","M",Color(0xFF0000FF)),
        TransferData("ONYEDIKACHI AKACHUKWU","7066908262 OPay","Last transfer on Mar 19, 2026","OPay",Color(0xFF4CAF50))
    )

    val favoritesList = listOf(
        TransferData("AKACHUKWU ONYEDIKACHI","7066908262 OPay",null,"OPay",Color(0xFF4CAF50)),
        TransferData("ONYEDIKACHI AKACHUKWU","7066908262 GT Bank",null,"GTB",Color(0xFFFF9800)),
        TransferData("JOY AKACHUKWU","5818115273 Moniepoint",null,"M",Color(0xFF0000FF)),
        TransferData("CHIKE AKACHUKWU","5818115273 Moniepoint","Last transfer on Mar 14, 2026","M",Color(0xFF0000FF)),
        TransferData("ONYEDIKACHI AKACHUKWU","7066908262 GT BANK",null,"GTB",Color(0xFFFF9800))
    )

    val contactsList = listOf(
        TransferData("AKACHUKWU ONYEDIKACHI","7066908262",null,null,Color(0xFF4CAF50),R.drawable.quick_pay_avatar),
        TransferData("ONYEDIKACHI AKACHUKWU","7066908262",null,null,Color(0xFFFF9800),R.drawable.quick_pay_avatar),
        TransferData("JOY AKACHUKWU","5818115273",null,null,Color(0xFF0000FF),R.drawable.quick_pay_avatar),
        TransferData("CHIKE AKACHUKWU","5818115273",null,null,Color(0xFF009688),R.drawable.quick_pay_avatar),
        TransferData("ONYEDIKACHI AKACHUKWU","7066908262",null,null,Color(0xFFFF9800),R.drawable.quick_pay_avatar)
    )

    val currentList = remember(selectedHistoryTab, searchQuery) {
        val baseList = when (selectedHistoryTab) {
            0 -> recentList
            1 -> favoritesList
            else -> contactsList
        }

        if (searchQuery.isBlank()) baseList
        else baseList.filter {
            it.title.contains(searchQuery, ignoreCase = true) ||
                    (it.subtitle?.contains(searchQuery, ignoreCase = true) == true)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    TextField(
                        value = accountNumber,
                        onValueChange = {
                            if (it.all { c -> c.isDigit() } && it.length <= 10) accountNumber = it
                        },
                        placeholder = { Text("Enter 10-digit Account No.", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xFF6200EE),
                            unfocusedIndicatorColor = Color.LightGray
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // --- Conditional Bank / QuickPay Row ---
                    if (selectedTab == 1) { // QuickPay tab
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF7A5AF8)), // Purple background
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "P",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = "QuickPay",
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        }
                    } else {
                        // Original To Bank row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { }
                                .padding(vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Select bank", color = Color.Gray, modifier = Modifier.weight(1f))
                            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                        }
                    }

                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { },
                        enabled = accountNumber.length == 10,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF7A5AF8), // Purple background
                            disabledContainerColor = Color(0xFFBFA7FF) // Lighter purple when disabled
                        )
                    ) {
                        Text(
                            text = "Next",
                            color = Color.White, // White text
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            InstantTransferCard()
            Spacer(modifier = Modifier.height(16.dp))
            SuccessMonitor()
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ScrollableTabRow(
                    selectedTabIndex = selectedHistoryTab,
                    containerColor = Color.Transparent,
                    edgePadding = 0.dp,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedHistoryTab]),
                            height = 2.dp,
                            color = Color(0xFF6200EE)
                        )
                    },
                    divider = {},
                    modifier = Modifier.weight(1f)
                ) {
                    historyTabs.forEachIndexed { index, title ->
                        val isSelected = selectedHistoryTab == index
                        Tab(
                            selected = isSelected,
                            onClick = { onHistoryTabChange(index) },
                            text = {
                                Text(
                                    text = title,
                                    fontSize = 16.sp,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                    color = if (isSelected) Color(0xFF6200EE) else Color.Gray
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.clickable {
                        isSearchActive = !isSearchActive
                    }
                )
            }

            if (isSearchActive) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
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
        }

        items(currentList) {
            TransferItem(it)
        }
    }
}

@Composable
fun InstantTransferCard() {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF7A5AF8),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("INSTANT")
                    }

                    withStyle(
                        style = SpanStyle(color = Color.Gray)) {
                        append(" | ")
                    }

                    withStyle(
                        style = SpanStyle(color = Color.Black)) {
                        append("Seamless transfers without delay")
                    }
                },
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Medium,
                fontSize = 15.6.sp
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun SuccessMonitor() {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cardiology),
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                "Bank transfer success rate monitor",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Composable
fun TransferItem(data: TransferData) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {

            if (data.avatarImage != null) {
                Image(
                    painter = painterResource(id = data.avatarImage),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(data.color),
                    contentAlignment = Alignment.Center
                ) {
                    data.avatar?.let {
                        Text(it, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(data.title, fontWeight = FontWeight.Medium, fontSize = 18.sp)

            if (!data.subtitle.isNullOrEmpty()) {
                Text(data.subtitle, color = Color.Gray, fontSize = 15.sp)
            }

            if (!data.date.isNullOrEmpty()) {
                Text(data.date, color = Color.Gray, fontSize = 15.sp)
            }
        }
    }
}