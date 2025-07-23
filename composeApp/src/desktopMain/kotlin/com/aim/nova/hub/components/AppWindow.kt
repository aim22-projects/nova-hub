package com.aim.nova.hub.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState

@Composable
fun AppWindow(
    windowState: WindowState,
    onClose: () -> Unit,
    content: @Composable FrameWindowScope.() -> Unit
) = Window(
    state = windowState,
    title = "Nova Hub",
    alwaysOnTop = true,
    resizable = false,
    undecorated = true,
    transparent = true,
    onCloseRequest = onClose,
    content = content
)
