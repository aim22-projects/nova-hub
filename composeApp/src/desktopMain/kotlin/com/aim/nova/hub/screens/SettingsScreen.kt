package com.aim.nova.hub.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.aim.nova.hub.navigation.AppScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SettingsScreen(onBack: (AppScreen) -> Unit) {
    Text("Settings Screen")
}