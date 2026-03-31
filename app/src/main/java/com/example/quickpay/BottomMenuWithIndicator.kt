package com.example.quickpay

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quickpay.destinations.FinanceScreenDestination
import com.example.quickpay.destinations.HomeScreenDestination
import com.example.quickpay.destinations.RewardScreenDestination
import com.example.quickpay.destinations.SettingsScreenDestination
import com.example.quickpay.model.BottomNavItem
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun BottomMenuWithIndicator(
    navController: NavController,
    indicatorColor: Color = Color(0xFF6200EE)
) {
    val items = listOf(
        BottomNavItem(
            label = "Home",
            iconDrawable = R.drawable.home_menu,
            direction = HomeScreenDestination
        ),
        BottomNavItem(
            label = "Reward",
            iconDrawable = R.drawable.ic_reward,
            direction = RewardScreenDestination
        ),
        BottomNavItem(
            label = "Finance",
            iconDrawable = R.drawable.trending,
            direction = FinanceScreenDestination
        ),
        BottomNavItem(
            label = "Profile",
            iconDrawable = R.drawable.user,
            direction = SettingsScreenDestination
        )
    )

    // Detect current route
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val currentIndex = items.indexOfFirst { it.direction.route == currentRoute }.coerceAtLeast(0)

    // Calculate item width based on screen width
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth / items.size

    // Animate the indicator position
    val indicatorOffset by animateDpAsState(
        targetValue = itemWidth * currentIndex,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "bottom_nav_indicator"
    )

    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {

        NavigationBar(containerColor = Color.Transparent) {

            items.forEachIndexed { index, item ->
                val selected = index == currentIndex
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(item.direction.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconDrawable!!),
                            contentDescription = item.label,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            fontSize = 13.sp, // main size
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = indicatorColor,
                        selectedTextColor = indicatorColor,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
        // Sliding indicator
        Box(
            Modifier
                .align(Alignment.BottomStart)
                .offset(x = indicatorOffset)
                .width(itemWidth)
                .height(4.dp)
                .background(indicatorColor)
        )
    }
}
