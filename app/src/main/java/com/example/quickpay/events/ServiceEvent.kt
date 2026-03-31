package com.example.quickpay.events

sealed class ServiceEvent {
    object Airtime : ServiceEvent()
    object Betting : ServiceEvent()
    object Data : ServiceEvent()
    object Electricity : ServiceEvent()
    object Insurance : ServiceEvent()
    object Loan : ServiceEvent()
    object TV : ServiceEvent()
    object More : ServiceEvent()
}
