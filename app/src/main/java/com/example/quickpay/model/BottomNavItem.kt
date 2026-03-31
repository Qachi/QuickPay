package com.example.quickpay.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

data class BottomNavItem(
    val label: String,
    val iconVector: ImageVector? = null, // For default Material icons
    val iconDrawable: Int? = null, // For custom icons
    val direction: DirectionDestinationSpec
)