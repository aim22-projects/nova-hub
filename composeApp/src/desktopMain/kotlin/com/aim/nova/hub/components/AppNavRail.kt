package com.aim.nova.hub.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

val topItems = listOf(
    NavItem("home", Icons.Default.Home, "Home"),
    NavItem("about", Icons.Default.Info, "About")
)
val bottomItems = listOf(
    NavItem("settings", Icons.Default.Settings, "Settings")
)


@Composable
fun AppNavRail(navController: NavController)
{
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val navItemSize : Dp = 36.dp

    NavigationRail (modifier = Modifier.fillMaxHeight()
        .wrapContentWidth()
        .width(navItemSize + 12.dp) // ⬅️ Slim rail
        .padding(vertical = 6.dp), // ⬅️ Reduce vertical margin
    ){
        // Top Items
        topItems.forEach { item ->
            NavigationRailTooltipItem(item, size = navItemSize, currentRoute == item.route)
            { navController.navigate(item.route) }
        }

        Spacer(modifier = Modifier.weight(1f)) // Push bottom items down

        bottomItems.forEach { item ->
            NavigationRailTooltipItem(item, size = navItemSize, currentRoute == item.route)
            { navController.navigate(item.route) }
        }
    }
}