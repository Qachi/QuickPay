package com.example.quickpay.model

import androidx.compose.ui.graphics.Color

enum class TransactionType {
    SEND, RECEIVE
}
data class Transaction(
    val title: String,
    val date: String,
    val month: String,
    val amount: String,
    val type: TransactionType,
    val status: String
) {
    val amountColor: Color
        get() = if (type == TransactionType.RECEIVE)
            Color.Green
        else Color.Red
}
