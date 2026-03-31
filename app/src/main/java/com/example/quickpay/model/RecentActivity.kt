package com.example.quickpay.model

sealed class RecentActivity {
    data class TrialCash(
        val title: String,
        val subtitle: String
    ) : RecentActivity()

    data class MoneyReceived(
        val sender: String,
        val amount: String,
        val dateTime: String
    ): RecentActivity()
}