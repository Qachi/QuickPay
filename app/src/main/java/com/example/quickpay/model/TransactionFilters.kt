package com.example.quickpay.model

enum class MoneyFlowFilter(val label: String) {
    ALL("All"),
    MONEY_IN("Money in"),
    MONEY_OUT("Money Out")
}

enum class AppFilter(val label: String) {
    ALL("All"),
    QuickPay("QuickPay"),
    QuickPay_BUSINESS("QuickPay Business")
}

enum class PaymentTypeFilter(val label: String) {
    ALL("All Categories"),
    MONEY_TRANSFER("Money Transfer"),
    ADD_MONEY("Add Money"),
    REFUND("Refund"),
    INTERBANK_TRANSFER("Interbank Transfer"),
    WITHDRAW("Withdraw")
}