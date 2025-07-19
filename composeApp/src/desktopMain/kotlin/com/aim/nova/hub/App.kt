package com.aim.nova.hub

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aim.nova.hub.components.AppNavRail
import com.aim.nova.hub.components.DraggableAppTopBar
import com.aim.nova.hub.screens.AboutScreen
import com.aim.nova.hub.screens.HomeScreen
import com.aim.nova.hub.screens.SettingsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun WindowScope.App(windowState: WindowState, onClose: () -> Unit) = MaterialTheme {
    val navController = rememberNavController()
    val colorScheme = MaterialTheme.colorScheme
    Scaffold(
        topBar = {
            DraggableAppTopBar(
                windowState,
                onCloseRequest = onClose
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .border(
                border = BorderStroke(1.dp, colorScheme.outlineVariant), // custom border
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp)) // <--- important to prevent overflow
            .padding(vertical = 0.dp),
    ) {
        paddingValues ->
        Row(modifier = Modifier.fillMaxSize().padding(top = paddingValues.calculateTopPadding()),)
        {
            AppNavRail(navController)
            // Right side: NavHost for content
            Box( modifier = Modifier.fillMaxSize().padding(all = 16.dp) )
            {
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