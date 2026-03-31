package com.example.quickpay.model

import androidx.compose.ui.graphics.Color

data class TransferData(
    val title: String,
    val subtitle: String? = null,
    val date: String? = null,
    val avatar: String? = null,
    val color: Color,
    val avatarImage: Int? = null
)