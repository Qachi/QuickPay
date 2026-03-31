package com.example.quickpay.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quickpay.BottomMenuWithIndicator
import com.example.quickpay.NavGraphs
import com.example.quickpay.R
import com.example.quickpay.appScaffold.AppScaffold
import com.example.quickpay.bottomsheet.BottomSheetContent
import com.example.quickpay.destinations.AddMoneyScreenDestination
import com.example.quickpay.destinations.CashBoxAutoSaveScreenDestination
import com.example.quickpay.destinations.HomeScreenDestination
import com.example.quickpay.destinations.MoreServicesScreenDestination
import com.example.quickpay.destinations.QuickPayCardScreenDestination
import com.example.quickpay.destinations.QuickPayScreenDestination
import com.example.quickpay.destinations.SavingsScreenDestination
import com.example.quickpay.destinations.TransactionHistoryScreenDestination
import com.example.quickpay.destinations.TransferScreenDestination
//import com.example.quickpay.destinations.TransferScreenDestination
import com.example.quickpay.ui.theme.QuickPayTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.launch


@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    // Detect if current destination is Home
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Home detection
    val isHome = currentRoute == HomeScreenDestination.route

    val showBottomMenu = currentRoute !in listOf(
        MoreServicesScreenDestination.route,
        TransactionHistoryScreenDestination.route,
        AddMoneyScreenDestination.route,
        CashBoxAutoSaveScreenDestination.route,
        TransferScreenDestination.route,
        QuickPayScreenDestination.route,
        SavingsScreenDestination.route,
        QuickPayCardScreenDestination.route
    )

    // AppScaffold handles the NavigationDrawer
    AppScaffold(
        drawerState = drawerState,

        gesturesEnabled = false
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            if (isHome) {
                // Only show BottomSheetScaffold on Home
                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "QuickPay") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.White,
                                titleContentColor = MaterialTheme.colorScheme.onSurface,
                                navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                            ),
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.menu),
                                        contentDescription = "Open Drawer",
                                        tint = Color(0xFF6200EE),
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Filled.HeadsetMic,
                                        contentDescription = "Contact Support",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                IconButton(onClick = { }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_custom_scanner),
                                        contentDescription = "Scan QR Code",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                IconButton(onClick = {
                                    scope.launch {
                                        if (scaffoldState.bottomSheetState.currentValue == SheetValue.PartiallyExpanded)
                                            scaffoldState.bottomSheetState.expand()
                                        else
                                            scaffoldState.bottomSheetState.partialExpand()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Notifications,
                                        contentDescription = "View Notifications",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        )
                    },
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(
                                    min = 140.dp,
                                    max = (LocalConfiguration.current.screenHeightDp.dp - 80.dp)
                                )
                        ) {
                            BottomSheetContent(
                                sheetState = scaffoldState.bottomSheetState,
                                scope = scope
                            )
                        }
                    },
                    sheetPeekHeight = 141.dp,
                    sheetContainerColor = Color(0xFF6200EE),
                    sheetDragHandle = null,
                    sheetMaxWidth = Dp.Unspecified,
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController
                        )
                    }
                }
            } else {
                // If not Home(OTHER SCREENS), show normal content only (no bottom sheet)
                Box(modifier = Modifier.fillMaxSize()) {
                    DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
                }
            }
            // Bottom Menu
            if (showBottomMenu) {
                Box(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    BottomMenuWithIndicator(navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    QuickPayTheme {
        MainScreen()
    }
}
