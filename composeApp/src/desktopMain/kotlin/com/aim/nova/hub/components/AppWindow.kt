package com.aim.nova.hub.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

@Composable
fun AppWindow(
    onClose: () -> Unit,
    content: @Composable FrameWindowScope.() -> Unit
) {
    val windowState: WindowState = rememberWindowState(
        size = DpSize(400.dp, 660.dp),
        position = WindowPosition(Alignment.BottomEnd)
    )
    Window(
        state = windowState,
        title = "Nova Hub",
        resizable = false,
        undecorated = true,
        transparent = true,
        onCloseRequest = onClose,
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    border = BorderStroke(2.dp, Color.Gray), // custom border
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp)) // <--- important to prevent overflow
                .background(Color.Gray) // background inside border
                .padding(2.dp)
        ) {
            // Your UI here
            Column(Modifier.fillMaxSize().background(Color.White)) {
                // Custom Title Bar acting as draggable area
                AppDraggableTopBar(
                    windowState,
                    onCloseRequest = onClose
                )
                // Rest of your window content
                content()
            }
        }
    }
}