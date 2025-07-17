package com.aim.nova.hub.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import novahub.composeapp.generated.resources.Res
import novahub.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun WindowScope.AppDraggableTopBar(windowState: WindowState, onCloseRequest: () -> Unit)
{
    WindowDraggableArea {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.DarkGray) // <--- Add a distinct background
//                .border(2.dp, Color.Red)
        ) {
            AppTitleBar(windowState, onCloseRequest)
        }
    }
}

/*
* Use directly in Window.WindowDraggableArea
* Window
* {
*   WindowDraggableArea {
*       // here
*       AppTitleBar(...)
*       {...}
*   }
* }
* */
@Composable
@Preview
fun AppTitleBar(windowState: WindowState, onCloseRequest: () -> Unit) {

    // WindowDraggableArea allows dragging the window from this composable
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color(0xFF1E1E1E))
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // App Title
            Text(stringResource(Res.string.app_name), color = Color.White, modifier = Modifier.weight(1f))

            // Minimize Button
            IconButton(onClick = {
                windowState.isMinimized = true
            }) {
                Icon(
                    imageVector = Icons.Default.Minimize,
                    contentDescription = "Minimize Window",
                    tint = Color.White
                )
            }

            // Close Button
            IconButton(
                onClick = onCloseRequest// onCloseRequest

            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Window",
                    tint = Color.White
                )
            }
        }

}