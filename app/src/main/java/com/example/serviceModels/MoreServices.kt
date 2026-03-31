package com.example.serviceModels

import com.example.quickpay.R

sealed class MoreServices(
    val label: String,
    val iconDrawables: Int
) {
    // Recommend
    object Withdraw : MoreServices("Withdraw", R.drawable.withdraw)
    object MyBusinessHub : MoreServices("My Business Hub", R.drawable.marketplace)

    // Wealth
    object CashBox : MoreServices("CashBox", R.drawable.cashbox)
    object Insurance : MoreServices("Insurance", R.drawable.insurance_agent)
    object SmartEarn : MoreServices("SmartEarn", R.drawable.smart_earn)
    object TrialCash : MoreServices("Trial Cash", R.drawable.trial_cash)
    object FixedSavings : MoreServices("Fixed Savings", R.drawable.fixed_savings)
    object SafeBox : MoreServices("SafeBox", R.drawable.safebox_6)
    object SpendAndSave : MoreServices("Spend & Save", R.drawable.smartphone)
    object TargetSavings : MoreServices("Target Savings", R.drawable.target_savings)
    object MutualFunds : MoreServices("Mutual Funds", R.drawable.mutual_funds)

    // Bill Payments
    object Airtime : MoreServices("Airtime", R.drawable.airtime)
    object Data : MoreServices("Data", R.drawable.data)
    object Betting : MoreServices("Betting", R.drawable.betting)
    object Electricity : MoreServices("Electricity", R.drawable.electricity)
    object Tv : MoreServices("Tv", R.drawable.television)
    object GiftCard : MoreServices("Gift Card", R.drawable.giftcard_)
    object Jamb : MoreServices("Jamb", R.drawable.jamb)
    object CowryCard : MoreServices("Cowry Card", R.drawable.cowry_card)
    object Water : MoreServices("Water", R.drawable.water_1)
    object WAEC : MoreServices("WAEC", R.drawable.waec)
    object TransportAndToll : MoreServices("Transport & Toll", R.drawable.transport_toll_2)
    object Education : MoreServices("Education", R.drawable.education_1)
    object GovernmentPayments : MoreServices("Government Payments", R.drawable.govt_payments)
    object TravelAndHotel : MoreServices("Travel & Hotel", R.drawable.travel_hotel)
    object Transport : MoreServices("Transport", R.drawable.luggage)
    object Internet : MoreServices("Internet", R.drawable.internet_4)

    // Transfer
    object ToQuickPay : MoreServices("To QuickPay", R.drawable.ic_follower)
    object ToBank : MoreServices("To Bank", R.drawable.bank)
    object QRCode : MoreServices("QR Code", R.drawable.qr_code)
    object PayMe : MoreServices("Pay Me", R.drawable.letter_n)
    object PayShop : MoreServices("Pay Shop", R.drawable.marketplace_2)

    //Others
    object ATMCard : MoreServices("ATM Card", R.drawable.atm_card_5)
    object AIAvatar : MoreServices("AI Avatar", R.drawable.ai_avatar1)
    object MyPOSReceipt : MoreServices("My POS Receipt", R.drawable.pos_receipt)
}