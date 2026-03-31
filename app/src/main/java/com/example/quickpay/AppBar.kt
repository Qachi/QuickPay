package com.example.quickpay

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quickpay.ui.theme.ScreenBackground
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    navigator: DestinationsNavigator,
    showMenu: Boolean = false
) {

    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold
            )
        },

        navigationIcon = {
            IconButton(onClick = {
                navigator.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },

        actions = {
            if (showMenu) { // 👈 ONLY SHOW WHEN TRUE
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu"
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {

                        DropdownMenuItem(
                            text = { Text("Interest Menu") },
                            onClick = {
                                expanded = false
                                // TODO: Handle click
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Percent,
                                    contentDescription = null
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Earnings Report") },
                            onClick = {
                                expanded = false
                                // TODO: Handle click
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Assessment,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ScreenBackground
        )
    )
}