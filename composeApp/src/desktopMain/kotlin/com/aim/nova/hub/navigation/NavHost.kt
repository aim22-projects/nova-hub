package com.aim.nova.hub.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aim.nova.hub.screens.AboutScreen
import com.aim.nova.hub.screens.HomeScreen
import com.aim.nova.hub.screens.SettingsScreen

@Composable
fun AppNavHost(onNavHostReady: suspend (NavController) -> Unit = {})
{
    val navController = rememberNavController()

//    NavHost(navController = navController, startDestination = AppScreen.Home.name) {
//        composable(AppScreen.Home.name) {
//            HomeScreen { navController.navigate(it) }
//        }
//        composable(AppScreen.About.name) {
//            AboutScreen { navController.popBackStack() }
//        }
//        composable(AppScreen.Settings.name) {
//            SettingsScreen { navController.popBackStack() }
//        }
//    }
//    LaunchedEffect(navController) {
//        onNavHostReady(navController)
//    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Multiplatform App") })
        }
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(){
                    navController.navigate(it);
                }
            }
            composable("about") {
                AboutScreen(){
                    navController.popBackStack()
                }
            }
            composable("settings") {
                SettingsScreen(){
                    navController.popBackStack()
                }
            }
        }
    }
}