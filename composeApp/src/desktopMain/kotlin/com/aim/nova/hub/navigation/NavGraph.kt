//// desktopMap/navigation/NavGraph.kt
//package com.aim.nova.hub.navigation
//
//import androidx.compose.runtime.*
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.ui.Modifier
//import com.aim.nova.hub.screens.AboutScreen
//import com.aim.nova.hub.screens.HomeScreen
//import com.aim.nova.hub.screens.SettingsScreen
//
//@Composable
//fun NavGraph(
//    modifier: Modifier = Modifier,
//    startScreen: AppScreen = AppScreen.Home
//) {
//    var currentScreen by remember { mutableStateOf<AppScreen>(startScreen) }
//
//    // Pass a lambda to switch screen
//    when (val screen = currentScreen) {
//        is AppScreen.Home.name -> HomeScreen(
//            onNavigate = { newScreen -> currentScreen = newScreen }
//        )
//
//        is AppScreen.Settings.name -> SettingsScreen(
//            onBack = { currentScreen = AppScreen.Home }
//        )
//
//        is AppScreen.About -> AboutScreen(
//            onBack = { currentScreen = AppScreen.Home }
//        )
//
//        else -> HomeScreen {
//            newScreen -> currentScreen = newScreen
//        }
//
////        is Screen.Detail -> DetailScreen(
////            id = screen.id,
////            onBack = { currentScreen = Screen.Home }
////        )
//    }
//}
