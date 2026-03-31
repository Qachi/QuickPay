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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.events.TransferEvent
import com.example.serviceModels.TransferActionServices

val transferActionServices = listOf(
    TransferActionServices.ToBank,
    TransferActionServices.ToQuickPay,
    TransferActionServices.Savings,
    TransferActionServices.ATMCard
)
@Composable
fun TransferActionItems(
    action: TransferActionServices,
    onClick: (TransferActionServices) -> Unit
) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .clickable { onClick(action) }
            .background(Color.White, RoundedCornerShape(14.dp))
            .padding(vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(action.iconDrawable),
            contentDescription = action.label,
            tint = action.tint,
            modifier = Modifier.size(36.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = action.label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp,
        )
    }
}
@Composable
fun TransferActionsSection(
    actions: List<TransferActionServices>,
    onTransferClick: (TransferEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        actions.forEach { action ->
            TransferActionItems(action) {
                onTransferClick(it.event)
            }
        }
    }
}



