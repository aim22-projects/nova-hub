package com.aim.nova.hub.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
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
import com.aim.nova.hub.navigation.Routes

val topItems = listOf(
    NavItem(Routes.HOME, Icons.Default.Home, "Home"),
    NavItem(Routes.DEVICES, Icons.Default.Devices, "Devices"),
)
val bottomItems = listOf(
    NavItem(Routes.ABOUT, Icons.Default.Info, "About"),
    NavItem(Routes.SETTINGS, Icons.Default.Settings, "Settings")
)


@Composable
fun AppNavRail(navController: NavController, paddingValues: PaddingValues)
{
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val navItemSize : Dp = 36.dp

    NavigationRail (modifier =
        Modifier.fillMaxHeight()
        .wrapContentWidth()
        .width(navItemSize + 12.dp) // ⬅️ Slim rail
        .padding(top = paddingValues.calculateTopPadding()), // ⬅️ Reduce vertical margin
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