package com.example.quickpay.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.R

@Composable
fun AvailableBalanceSection(
    balance: String = "₦250,000.23",
    onTransactionHistoryClick: () -> Unit,
    onAddMoneyClick: () -> Unit,
    onEnableInterestClick: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
    ) {
        // TOP PURPLE CONTAINER
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE9E6FF), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            // ---------- TOP ROW ----------
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // LEFT: Available Balance + Eye
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.security_check_mark),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Available Balance",
                        fontSize = 16.sp,
                        color = Color(0xFF1A1A1A)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        painter = painterResource(
                            id =
                                if (isVisible) R.drawable.visibility else R.drawable.visibility_off
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { isVisible = !isVisible },
                        tint = Color(0xFF6200EE)
                    )
                }
                // RIGHT: Transaction History >
                Row(
                    modifier = Modifier.clickable { onTransactionHistoryClick() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Transaction History",
                        fontSize = 14.sp,
                        color = Color(0xFF1A1A1A)
                    )
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color(0xFF6200EE)
                    )
                }

            }
            Spacer(modifier = Modifier.height(12.dp))

            // -------- BOTTOM ROW --------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isVisible) balance else "****",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1A1A1A)
                )
                Button(
                    onClick = { onAddMoneyClick() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "Add Money",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }

        // WHITE SECTION BELOW PURPLE
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.piggybank_1),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Enable interest on your balance",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1A1A1A)
                )
            }

            Row(
                modifier = Modifier.clickable {
                    onEnableInterestClick()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Enable Now",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6200EE)
                )
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Arrow",
                    tint = Color(0xFF6200EE)
                )
            }
        }
    }
}