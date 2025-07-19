package com.aim.nova.hub.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import novahub.composeapp.generated.resources.Res
import novahub.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun WindowScope.DraggableAppTopBar(windowState: WindowState, onCloseRequest: () -> Unit) = WindowDraggableArea {
    AppTopBar (windowState, onCloseRequest)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(windowState: WindowState, onCloseRequest: () -> Unit) = TopAppBar (
    modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .heightIn(48.dp),
    title = {
        // Use a Row with verticalArrangement to center the title
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(Res.string.app_name), style = MaterialTheme.typography.titleMedium)
        } },
    actions = {
        Row(
            modifier = Modifier.fillMaxHeight().padding(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton({ windowState.isMinimized = true},
                shape = RoundedCornerShape(8.dp), // Rounded corners
                contentPadding = PaddingValues(0.dp), // No extra padding
                modifier = Modifier.size(36.dp) // Square: equal width and height
            ){
                Icon(
                    imageVector = Icons.Default.Minimize,
                    contentDescription = "Minimize Window",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(Modifier.size(4.dp))
            TextButton(onCloseRequest,
                shape = RoundedCornerShape(8.dp), // Rounded corners
                contentPadding = PaddingValues(0.dp), // No extra padding
                modifier = Modifier.size(36.dp) // Square: equal width and height
            ){
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Window",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
)