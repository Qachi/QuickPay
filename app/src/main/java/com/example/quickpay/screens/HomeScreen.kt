package com.example.quickpay.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quickpay.carouselAndIndicator.PromoCardCarousel
import com.example.quickpay.destinations.AddMoneyScreenDestination
import com.example.quickpay.destinations.CashBoxAutoSaveScreenDestination
import com.example.quickpay.destinations.MoreServicesScreenDestination
import com.example.quickpay.destinations.QuickPayCardScreenDestination
import com.example.quickpay.destinations.QuickPayScreenDestination
import com.example.quickpay.destinations.SavingsScreenDestination
import com.example.quickpay.destinations.TransactionHistoryScreenDestination
import com.example.quickpay.destinations.TransferScreenDestination
import com.example.quickpay.events.ServiceEvent
import com.example.quickpay.events.TransferEvent
import com.example.quickpay.sections.AvailableBalanceSection
import com.example.quickpay.sections.RecentActivityAutoSlider
import com.example.quickpay.sections.ServiceSection
import com.example.quickpay.sections.TransferActionsSection
import com.example.quickpay.sections.services
import com.example.quickpay.sections.transferActionServices
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F5F7))
    ) {
        // --- MOVE YOUR SECTIONS INSIDE THE COLUMN ---
        AvailableBalanceSection(
            onTransactionHistoryClick = {
                navigator.navigate(TransactionHistoryScreenDestination)
            },
            onAddMoneyClick = {
                navigator.navigate(AddMoneyScreenDestination)
            },
            onEnableInterestClick = {
                navigator.navigate(CashBoxAutoSaveScreenDestination)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TransferActionsSection(
            actions = transferActionServices,
            onTransferClick = { transferEvent ->
                when (transferEvent) {
                    TransferEvent.ToBank -> {
                        navigator.navigate(TransferScreenDestination)
                    }

                    TransferEvent.ToQuickPay -> {
                        navigator.navigate(QuickPayScreenDestination)
                    }

                    TransferEvent.Savings -> { navigator.navigate(SavingsScreenDestination)
                    }

                    TransferEvent.ATMCard -> {navigator.navigate(QuickPayCardScreenDestination)
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        RecentActivityAutoSlider()
        Spacer(modifier = Modifier.height(12.dp))
        ServiceSection(
            services = services,
            onServiceClick = { serviceEvent ->
                when (serviceEvent) {
                    ServiceEvent.Airtime -> { /* Handle airtime click */
                    }

                    ServiceEvent.Betting -> { /* Handle betting click */
                    }

                    ServiceEvent.Data -> { /* Handle data click */
                    }

                    ServiceEvent.Electricity -> { /* Handle electricity click */
                    }

                    ServiceEvent.Insurance -> { /* Handle insurance click */
                    }

                    ServiceEvent.Loan -> { /* Handle loan click */
                    }

                    ServiceEvent.TV -> { /* Handle TV click */
                    }

                    ServiceEvent.More -> {
                        navigator.navigate(MoreServicesScreenDestination)
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        PromoCardCarousel()
    }
}


