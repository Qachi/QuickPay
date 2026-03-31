package com.example.quickpay.data

import com.example.quickpay.model.Transaction
import com.example.quickpay.model.TransactionType

object TransactionDummy {

    val transactions = listOf(

        // FEB 2026
        Transaction(
            "Top Up Airtime",
            "Feb 03, 2026 11:29 AM",
            "Feb 2026",
            "-1,000.00",
            TransactionType.SEND,
            "Successful"
        ),
        Transaction(
            "Received from AKACHU...",
            "Feb 02, 2026 3:06 PM",
            "Feb 2026",
            "+300,634.00",
            TransactionType.RECEIVE,
            "Successful"
        ),
        Transaction(
            "Send - JOY AKACHUKWU...",
            "Feb 01, 2026 7:36 PM",
            "Feb 2026",
            "-500,000.00",
            TransactionType.SEND,
            "Processing"
        ),

        // JAN 2026
        Transaction("Top Up Airtime","Jan 20, 2026 11:29 AM","Jan 2026","-1,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from AKACHU...","Jan 17, 2026 3:06 PM","Jan 2026","+300,634.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - JOY AKACHUKWU...","Jan 16, 2026 7:36 PM","Jan 2026","-500,000.00",TransactionType.SEND,"Failed"),
        Transaction("Received from CHIKE...","Jan 15, 2026 3:06 PM","Jan 2026","+600,634.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - CHIKE UCHECHU...","Jan 13, 2026 7:36 PM","Jan 2026","-480,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from NDUBUISI UD...","Jan 12, 2026 3:06 PM","Jan 2026","+11,634.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - DAUDA ABDULLAHI","Jan 11, 2026 7:36 PM","Jan 2026","-8,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from JOY OBIA...","Jan 10, 2026 3:06 PM","Jan 2026","+300,500.00",TransactionType.RECEIVE,"Processing"),
        Transaction("Send - EJIKE KACHI","Jan 10, 2026 7:36 PM","Jan 2026","-18,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from OKE...","Jan 10, 2026 3:06 PM","Jan 2026","+100,000,500.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Top Up Airtime","Jan 09, 2026 11:29 AM","Jan 2026","-1,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from AKACHU...","Jan 09, 2026 3:06 PM","Jan 2026","+300,634.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - JOY AKACHUKWU...","Jan 08, 2026 7:36 PM","Jan 2026","-500,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from CHIKE...","Jan 07, 2026 3:06 PM","Jan 2026","+600,634.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - CHIKE UCHECHU...","Jan 07, 2026 7:36 PM","Jan 2026","-480,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from NDUBUISI UD...","Jan 05, 2026 3:06 PM","Jan 2026","+11,634.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - DAUDA ABDULLAHI","Jan 04, 2026 7:36 PM","Jan 2026","-8,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from JOY OBIA...","Jan 04, 2026 3:06 PM","Jan 2026","+300,500.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - EJIKE KACHI","Jan 03, 2026 7:36 PM","Jan 2026","-18,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from OKE...","Jan 01, 2026 3:06 PM","Jan 2026","+100,000,500.00",TransactionType.RECEIVE,"Successful"),

        // DEC 2025
        Transaction("Send - DAUDA ABDULLAHI","Dec 25, 2025 7:36 PM","Dec 2025","-8,000.00",TransactionType.SEND,"Successful"),
        Transaction("Send - EJIKE KACHI","Dec 25, 2025 7:36 PM","Dec 2025","-18,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from FID...","Dec 24, 2025 3:06 PM","Dec 2025","+1,000,000.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - CHIKA NGOZI","Dec 24, 2025 7:36 PM","Dec 2025","-280,000.00",TransactionType.SEND,"Successful"),
        Transaction("Send - CHERISH UGWU","Dec 23, 2025 7:36 PM","Dec 2025","-180,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from CHIKA...","Dec 23, 2025 3:06 PM","Dec 2025","+2,500,000.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - TREASURE UGWU","Dec 23, 2025 7:36 PM","Dec 2025","-180,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from OKE...","Dec 23, 2025 3:06 PM","Dec 2025","+2,500,000.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Received from CHIKA...","Dec 23, 2025 3:06 PM","Dec 2025","+2,500,000.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - CHERISH UGWU","Dec 19, 2025 7:36 PM","Dec 2025","-195,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from OKE...","Dec 17, 2025 3:06 PM","Dec 2025","+2,500,000.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Received from CHIKE...","Dec 16, 2025 3:06 PM","Dec 2025","+260,000.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - TREASURE UGWU","Dec 15, 2025 7:36 PM","Dec 2025","-110,000.00",TransactionType.SEND,"Failed"),
        Transaction("Received from JOY...","Dec 15, 2025 3:06 PM","Dec 2025","+256,000.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Received from NDUBUISI UD...","Dec 14, 2025 3:06 PM","Dec 2025","+11,634.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - DAUDA ABDULLAHI","Dec 13, 2025 3:06 PM","Dec 2025","-8,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from JOY OBIA...","Dec 12, 2025 3:06 PM","Dec 2025","+300,500.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Send - EJIKE KACHI","Dec 11, 2025 3:06 PM","Dec 2025","-18,000.00",TransactionType.SEND,"Successful"),
        Transaction("Received from OKE...","Dec 11, 2025 3:06 PM","Dec 2025","+100,000,500.00",TransactionType.RECEIVE,"Successful"),
        Transaction("Received from FID...","Dec 10, 2025 3:06 PM","Dec 2025","+800,000,500.00",TransactionType.RECEIVE,"Successful")
    )
}