// commonMain/kotlin/navigation/AppRoutes.kt
package com.aim.nova.hub.navigation;

import org.jetbrains.compose.resources.StringResource

import novahub.composeapp.generated.resources.Res
import novahub.composeapp.generated.resources.about_screen
import novahub.composeapp.generated.resources.app_name
import novahub.composeapp.generated.resources.home_screen
import novahub.composeapp.generated.resources.settings_screen

enum class AppScreen(val title: StringResource) {
    Start(Res.string.app_name),
    Home( Res.string.home_screen),
    About(Res.string.about_screen),
    Settings(Res.string.settings_screen),
//    data class Details(val id: Int): Screen
}

