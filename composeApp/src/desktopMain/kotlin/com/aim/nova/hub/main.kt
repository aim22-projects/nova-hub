package com.aim.nova.hub

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.TrayState
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState
import androidx.compose.ui.window.rememberWindowState
import com.aim.nova.hub.components.AppTheme
import com.aim.nova.hub.components.AppTrayIcon
import com.aim.nova.hub.components.AppWindow

fun main() = application {
    val windowState: WindowState = rememberWindowState(
        size = DpSize(400.dp, 660.dp),
        position = WindowPosition(Alignment.BottomEnd)
    )
    val trayState: TrayState = rememberTrayState()
    var isWindowVisible by remember { mutableStateOf(true) }

    AppTrayIcon( trayState ) { isWindowVisible = true }

    if (isWindowVisible)
        AppWindow(windowState, onClose = { isWindowVisible = false }){
            AppTheme {
                App(windowState,  onClose = { isWindowVisible = false })
            }
        }
}
