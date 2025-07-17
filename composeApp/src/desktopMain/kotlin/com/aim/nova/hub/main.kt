package com.aim.nova.hub

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.aim.nova.hub.components.AppDraggableTopBar
import com.aim.nova.hub.components.AppTrayIcon

fun main() = application {

    val trayState: TrayState = rememberTrayState()
    val windowState: WindowState = rememberWindowState(size = DpSize(400.dp, 660.dp), position = WindowPosition(
        Alignment.BottomEnd))
    var isWindowVisible by remember { mutableStateOf(true); }

    AppTrayIcon(
        trayState = trayState,
        showWindow = { isWindowVisible = true }
    )


    if (isWindowVisible)
        Window(
            state = windowState,
            title = "Nova Hub",
            resizable = false,
            undecorated = true,
//            transparent = true,
            onCloseRequest = { isWindowVisible = false },
        ) {
            Column(Modifier.fillMaxSize().background(Color.White)) {
                // Custom Title Bar acting as draggable area
                AppDraggableTopBar (windowState) { isWindowVisible = false }
                // Rest of your window content
                App()
            }
        }
}
