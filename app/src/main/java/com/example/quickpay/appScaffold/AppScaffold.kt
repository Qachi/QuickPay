package com.example.quickpay.appScaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.quickpay.model.MenuItem
import com.example.quickpay.navigation.DrawerBody
import com.example.quickpay.navigation.DrawerHead
import kotlinx.coroutines.launch

@Composable
fun AppScaffold(
    drawerState: DrawerState,
    gesturesEnabled: Boolean,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHead()
                DrawerBody(
                    listOf(
                        MenuItem(
                            id = "home",
                            title = "Home",
                            icon = Icons.Filled.Home,
                            contentDescription = "Go to home screen"
                        ),
                        MenuItem(
                            id = "settings",
                            title = "Settings",
                            icon = Icons.Filled.Settings,
                            contentDescription = "Go to settings screen"
                        ),
                        MenuItem(
                            id = "help",
                            title = "Help",
                            icon = Icons.Filled.Settings,
                            contentDescription = "Get help"
                        ),
                        MenuItem(

                            id = "logout",
                            title = "Logout",
                            icon = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Logout"
                        )
                    ),
                    onItemClick = {
                        println("Clicked on ${it.title}")
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        },
        content = content
    )
}