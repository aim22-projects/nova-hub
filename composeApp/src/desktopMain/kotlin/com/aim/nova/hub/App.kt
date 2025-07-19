package com.aim.nova.hub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aim.nova.hub.components.AppNavRail
import com.aim.nova.hub.screens.AboutScreen
import com.aim.nova.hub.screens.HomeScreen
import com.aim.nova.hub.screens.SettingsScreen
import novahub.composeapp.generated.resources.Res
import novahub.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() = MaterialTheme {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(Res.string.app_name)) })
        }
    ) {
        Row(modifier = Modifier.fillMaxSize())
        {
            AppNavRail(navController)
            // Right side: NavHost for content
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
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
        }
    }
}