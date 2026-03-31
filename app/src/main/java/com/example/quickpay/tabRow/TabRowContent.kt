package com.example.quickpay.tabRow

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Deblur
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.model.TabItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private val tabItems = listOf(
    TabItem("Notifications", Icons.Filled.Home),
    TabItem("Support", Icons.Filled.Deblur),
    TabItem("Settings", Icons.Filled.Verified),
    TabItem("Transaction History", Icons.Filled.Search)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabRowContent(
    items: List<TabItem> = tabItems,
    currentSelectedIndex: Int, // The current index, controlled by the parent
    onItemSelected: (index: Int) -> Unit, // A callback to notify the parent of a click
    sheetState: SheetState, // Pass the sheet state
    scope: CoroutineScope,  // Pass the coroutine scope
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val inactiveColor = Color.White.copy(alpha = 0.7f) // Or another alpha like 0.6f

    TabRow(
        modifier = modifier,
        selectedTabIndex = currentSelectedIndex,
        containerColor = Color(0xFF6200EE),
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[currentSelectedIndex]),
                height = 3.dp,
                color = Color.White // Indicator color
            )
        }
    ) {
        items.forEachIndexed { index, tabItem ->
            Tab(
                selected = (currentSelectedIndex == index),
                onClick = {
                    onItemSelected(index)
                    scope.launch {
                        if (sheetState.currentValue == SheetValue.PartiallyExpanded)
                            sheetState.expand()
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = inactiveColor,
                text = {
                    Text(
                        text = tabItem.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1, // PREVENT 2-LINE BREAK
                        overflow = TextOverflow.Ellipsis, // Prevents breaking UI
                        textAlign = TextAlign.Center // PERFECT CENTERING
                    )
                },
            )
        }
    }
}
