package com.example.serviceModels

import androidx.compose.ui.graphics.Color
import com.example.quickpay.R
import com.example.quickpay.events.TransferEvent

sealed class TransferActionServices(
    val label: String,
    val iconDrawable: Int,
    val event: TransferEvent,
    val tint: Color = Color.Unspecified
){
    // Payments
    object ToBank: TransferActionServices(
        label = "To Bank",
        iconDrawable = R.drawable.bank,
        event = TransferEvent.ToBank
    )
    object ToQuickPay: TransferActionServices(
        label = "To QuickPay",
        iconDrawable = R.drawable.ic_follower,
        event = TransferEvent.ToQuickPay
    )
    object Savings: TransferActionServices(
        label = "Savings",
        iconDrawable = R.drawable.piggy_savings,
        event = TransferEvent.Savings
    )
    object ATMCard: TransferActionServices(
        label = "ATM Card",
        iconDrawable = R.drawable.atm_card_1,
        event = TransferEvent.ATMCard
    )
}