package com.aim.nova.hub.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    NavigationRail (modifier =
        Modifier.fillMaxHeight()
        .wrapContentWidth()
        .width(navItemSize + 12.dp), // ⬅️ Slim rail
//        .padding(top = paddingValues.calculateTopPadding()), // ⬅️ Reduce vertical margin
//        containerColor = Color.DarkGray,
    ){
        // cannot add inner padding using direct NavigationRail.modifier which actually causes outer margin
        // so use Column.modifier to add inner padding
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp), // ✅ Inner top and bottom padding
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            // Top Items
            topItems.forEach { item ->
                NavItemTooltip(item.label) {
                    NavItem (item, size = navItemSize, currentRoute == item.route)
                    { navController.navigate(item.route) }
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Push bottom items down

            bottomItems.forEach { item ->
                NavItemTooltip(item.label) {
                    NavItem (item, size = navItemSize, currentRoute == item.route)
                    { navController.navigate(item.route) }
                }
            }
        }
    }
}