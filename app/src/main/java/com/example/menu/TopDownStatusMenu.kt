package com.example.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.R

@Composable
fun TopDownStatusMenu(
    selectedStatus: String,
    statuses: List<String> = listOf(
        "All Status",
        "Processing",
        "Successful",
        "Failed",
        "Cancelled",
        "Refunded"
    ),
    onStatusSelected: (String) -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 12.dp,
            bottomEnd = 12.dp
        ),
        modifier = Modifier.fillMaxWidth()
    ) {

        LazyColumn {

            itemsIndexed(statuses) { index, status ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onStatusSelected(status) }
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = status,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color =
                            if (status == selectedStatus)
                                Color(0xFF6200EE)
                            else
                                Color.Black
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    if (status == selectedStatus) {
                        Icon(
                            painter = painterResource(R.drawable.checkmark),
                            contentDescription = null,
                            tint = Color(0xFF6200EE),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                if (index < statuses.lastIndex) {
                    HorizontalDivider(
                        color = Color.Gray.copy(alpha = 0.25f),
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}