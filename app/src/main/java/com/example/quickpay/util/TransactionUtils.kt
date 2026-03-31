package com.example.quickpay.util
import com.example.quickpay.model.Transaction
import com.example.quickpay.model.TransactionType
import java.text.SimpleDateFormat
import java.util.Locale

fun extractMonth(date: String): String {
    val input = SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.ENGLISH)
    val output = SimpleDateFormat("MMM", Locale.ENGLISH)
    return try {
        output.format(input.parse(date)!!)
    } catch (e: Exception) {
        "Unknown"
    }
}

fun calculateTotals(transactions: List<Transaction>): Pair<String, String> {
    val incoming = transactions
        .filter { it.type == TransactionType.RECEIVE }
        .sumOf { it.amount.replace(",", "").replace("+", "").toDouble() }

    val outgoing = transactions
        .filter { it.type == TransactionType.SEND }
        .sumOf { it.amount.replace(",", "").replace("-", "").toDouble() }

    return Pair(
        "₦%,.2f".format(incoming),
        "₦%,.2f".format(outgoing)
    )
}
