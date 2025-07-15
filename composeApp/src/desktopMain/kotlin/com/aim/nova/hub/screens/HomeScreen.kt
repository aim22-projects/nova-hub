package com.aim.nova.hub.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.aim.nova.hub.navigation.AppScreen
import novahub.composeapp.generated.resources.Res
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    Column {
        Text("Home Screen")
        Button(onClick = {
//            onNavigate(AppScreen.Settings)
            onNavigate("settings")
        }) {
            Text("Go to Settings")
        }
        Button(onClick = {
//            onNavigate("settings")
            onNavigate("about")
        }) {
            Text("Go to About")
        }
//        Button(onClick = { onNavigate(AppScreen.Details(42)) }) {
//            Text("View Detail 42")
//        }
    }
}