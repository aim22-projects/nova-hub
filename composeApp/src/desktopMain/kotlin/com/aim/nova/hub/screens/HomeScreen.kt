package com.aim.nova.hub.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HomeScreen( navController: NavController ) {
    Column {
        Text("Home Screen")
        Button(onClick = {
            navController.navigate("settings")
        }) {
            Text("Go to Settings!")
        }
        Button(onClick = {
            navController.navigate("about")
        }) {
            Text("Go to About!")
        }
    }
}