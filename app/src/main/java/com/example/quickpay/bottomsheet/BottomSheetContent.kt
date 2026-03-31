package com.example.quickpay.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.tabRow.TabRowContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    sheetState: SheetState,
    scope: CoroutineScope
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // CUSTOM DRAG HANDLE (VISIBLE)
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(60.dp)
                .background(
                    color = Color.White.copy(alpha = 0.95f),
                    shape = RoundedCornerShape(50)
                )
        )

        // Header Box with the TabRow and IconButton
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TabRowContent(
                currentSelectedIndex = selectedTabIndex,
                onItemSelected = { index -> selectedTabIndex = index},
                sheetState = sheetState,
                scope = scope,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 48.dp)
            )
            IconButton(
                onClick = {
                    scope.launch {
                        if (sheetState.currentValue == SheetValue.PartiallyExpanded) {
                            sheetState.expand()
                        } else {
                            sheetState.partialExpand()
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                val iconImage =
                    if (sheetState.currentValue == SheetValue.PartiallyExpanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown
                Icon(
                    imageVector = iconImage,
                    contentDescription = "Toggle Bottom Sheet",
                    tint = Color.White
                )
            }
        }

        // ▼▼▼ THIS IS THE FIX ▼▼▼
        // This Column now fills the *remaining* space instead of the whole screen.
        Column(
            modifier = Modifier
                .weight(1f) // Replaces fillMaxSize()
                .fillMaxWidth() // Still want to fill the width
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Display content based on the selected tab
            when (selectedTabIndex) {
                0 -> Text(
                    "Home Details",
                    fontSize = 20.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )

                1 -> Text(
                    "Search Details",
                    fontSize = 20.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )

                2 -> Text(
                    "Settings Details",
                    fontSize = 20.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )

                3 -> Text(
                    "Profile Details",
                    fontSize = 20.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "More information and controls inProgress\n to be placed here.",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Current State: ${sheetState.currentValue}",
                color = Color.White
            )
            Text(
                "Target State: ${sheetState.targetValue}",
                color = Color.White
            )
        }
    }
}
