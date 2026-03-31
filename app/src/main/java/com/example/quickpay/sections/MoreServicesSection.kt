package com.example.quickpay.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.example.quickpay.AppBar
import com.example.quickpay.ui.theme.ScreenBackground
import com.example.serviceModels.MoreServices
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

val recommendServices = listOf(
    MoreServices.Withdraw, MoreServices.MyBusinessHub
)
val wealthServices = listOf(
    MoreServices.CashBox, MoreServices.Insurance, MoreServices.SmartEarn,
    MoreServices.TrialCash, MoreServices.FixedSavings, MoreServices.SafeBox,
    MoreServices.SpendAndSave, MoreServices.TargetSavings, MoreServices.MutualFunds,
)
val billPaymentsServices = listOf(
    MoreServices.Airtime,
    MoreServices.Data,
    MoreServices.Betting,
    MoreServices.Electricity,
    MoreServices.Tv,
    MoreServices.GiftCard,
    MoreServices.Jamb,
    MoreServices.CowryCard,
    MoreServices.Water,
    MoreServices.WAEC,
    MoreServices.TransportAndToll,
    MoreServices.Education,
    MoreServices.GovernmentPayments,
    MoreServices.TravelAndHotel,
    MoreServices.Transport,
    MoreServices.Internet
)
val TransferServices = listOf(
    MoreServices.ToQuickPay,
    MoreServices.ToBank,
    MoreServices.QRCode,
    MoreServices.PayMe,
    MoreServices.PayShop,
)
val OtherServices = listOf(
    MoreServices.ATMCard,
    MoreServices.AIAvatar,
    MoreServices.MyPOSReceipt
)

@Composable
fun MoreServicesItem(
    moreService: MoreServices,
    onClick: (MoreServices) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable { onClick(moreService) }
            .padding(vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = moreService.iconDrawables),
            contentDescription = moreService.label,
            modifier = Modifier.size(36.dp),
            tint = Color.Unspecified
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = moreService.label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MoreServiceSection(
    title: String,
    services: List<MoreServices>
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Section Title
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // FlowRow automatically wraps items to the next line
            // maxItemsInEachRow = 4 ensures the grid look you want
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 4,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Determine the width of one item (approx 1/4th of screen minus spacing)
                val itemModifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(0.25f)

                services.forEach { service ->
                    MoreServicesItem(
                        moreService = service,
                        onClick = { /* Handle Click */ },
                        modifier = itemModifier
                    )
                }
                // Add invisible spacers to fill the row if it's not full
                // (Optional calculation for perfect alignment on last row)
                val remainder = 4 - (services.size % 4)
                if (remainder < 4) {
                    repeat(remainder) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Destination
@Composable
fun MoreServicesScreen(
    navigator: DestinationsNavigator
) {
    Scaffold(
        containerColor = ScreenBackground,
        topBar = {
            AppBar(title = "My Service", navigator = navigator)
        }
    ) { paddingValues ->

        // Main List is a LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                MoreServiceSection(title = "Recommend", services = recommendServices)
            }
            item {
                MoreServiceSection(title = "Wealth", services = wealthServices)
            }
            item {
                MoreServiceSection(title = "Bill Payments", services = billPaymentsServices)
            }
            item {
                MoreServiceSection(title = "Transfer", services = TransferServices)
            }
            item {
                MoreServiceSection(title = "Others", services = OtherServices)
            }
            item {
                Spacer(Modifier.height(32.dp))
            }
        }
    }
}
