package com.example.serviceModels

import androidx.compose.ui.graphics.Color
import com.example.quickpay.R
import com.example.quickpay.events.ServiceEvent

sealed class Services(
    val label: String,
    val iconDrawable: Int,
    val event: ServiceEvent,
    val tint: Color = Color.Unspecified
) {
    // Services
    object Airtime : Services(
        label = "Airtime",
        iconDrawable = R.drawable.airtime,
        event = ServiceEvent.Airtime
    )
    object Betting : Services(
        label = "Betting",
        iconDrawable = R.drawable.betting,
        event = ServiceEvent.Betting
    )
    object Data : Services(
        label = "Data",
        iconDrawable = R.drawable.data,
        event = ServiceEvent.Data
    )
    object Electricity : Services(
        label = "Electricity",
        iconDrawable = R.drawable.electricity,
        event = ServiceEvent.Electricity
    )
    object Insurance : Services(
        label = "Insurance",
        iconDrawable = R.drawable.insurance_agent,
        event = ServiceEvent.Insurance
    )
    object Loan : Services(
        label = "Loan",
        iconDrawable = R.drawable.loan_loan,
        event = ServiceEvent.Loan,
        tint = Color(0xFF6200EE),
        )
    object TV : Services(
        label = "TV",
        iconDrawable = R.drawable.television,
        event = ServiceEvent.TV
    )
    object More : Services(
        label = "More",
        iconDrawable = R.drawable.more_menu,
        event = ServiceEvent.More,
        tint = Color(0xFF6200EE)
    )
}
