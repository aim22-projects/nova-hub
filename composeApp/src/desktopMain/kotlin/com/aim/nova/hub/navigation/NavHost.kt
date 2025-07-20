package com.aim.nova.hub.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aim.nova.hub.screens.AboutScreen
import com.aim.nova.hub.screens.DevicesScreen
import com.aim.nova.hub.screens.HomeScreen
import com.aim.nova.hub.screens.SettingsScreen

data object Routes {
    const val HOME = "home"
    const val DEVICES = "devices"
    const val ABOUT = "about"
    const val SETTINGS = "settings"
}


@Composable
fun AppNavHost(navController: NavHostController, paddingValues: PaddingValues)
= NavHost(navController = navController, startDestination = Routes.HOME, modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
{
    composable(Routes.HOME) {
        HomeScreen(navController)
    }
    composable(Routes.DEVICES) {
        DevicesScreen(navController)
    }
    composable(Routes.ABOUT) {
        AboutScreen(navController)
    }
    composable(Routes.SETTINGS) {
        SettingsScreen(navController)
    }
}
