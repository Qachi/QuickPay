package com.example.quickpay.events

sealed class TransferEvent {
     object ToBank : TransferEvent()
     object ToQuickPay : TransferEvent()
     object Savings : TransferEvent()
     object ATMCard : TransferEvent()
}