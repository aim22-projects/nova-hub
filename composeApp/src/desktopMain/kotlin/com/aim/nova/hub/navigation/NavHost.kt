package com.aim.nova.hub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aim.nova.hub.screens.AboutScreen
import com.aim.nova.hub.screens.HomeScreen
import com.aim.nova.hub.screens.SettingsScreen

@Composable
fun AppNavHost()
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("about") {
            AboutScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
    }
}