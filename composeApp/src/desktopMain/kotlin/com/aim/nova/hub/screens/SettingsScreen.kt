package com.aim.nova.hub.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aim.nova.hub.presentations.AppStateManager
import org.koin.compose.koinInject


@Composable
fun SettingsScreen(navController: NavController, appStateManager: AppStateManager = koinInject ()) {
    val currentSettings by appStateManager.appSettings.collectAsState()
    var nodePathInput by remember { mutableStateOf(currentSettings.nodePath) }


    // Update the input field when settings change externally
    LaunchedEffect(currentSettings.nodePath) {
        nodePathInput = currentSettings.nodePath
    }

    Column {
        ListItem(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            headlineContent = { Text("Settings") }
        )
        Column (
            Modifier.padding(8.dp)
        ) {
            OutlinedTextField(
                value = nodePathInput,
                onValueChange = { nodePathInput = it },
                label = { Text("Node.js Environment Path") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton({ appStateManager.updateNodePath(nodePathInput) } )
                    { Icons.Default.Save }
                }


            )

        }
    }

}