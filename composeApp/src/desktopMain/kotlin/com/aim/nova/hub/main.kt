package com.aim.nova.hub

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.application
import com.aim.nova.hub.components.AppTrayIcon
import com.aim.nova.hub.components.AppWindow

fun main() = application {
    var isWindowVisible by remember { mutableStateOf(true) }

    AppTrayIcon(
        onOpenWindow = { isWindowVisible = true }
    )

    if (isWindowVisible)
        AppWindow(onClose = { isWindowVisible = false }){
            App()
        }
}
