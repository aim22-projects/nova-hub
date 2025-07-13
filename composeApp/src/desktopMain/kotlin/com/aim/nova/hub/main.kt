package com.aim.nova.hub

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import novahub.composeapp.generated.resources.Res
import novahub.composeapp.generated.resources.icon
import org.jetbrains.compose.resources.painterResource

fun main() = application {

    val trayState: TrayState = rememberTrayState()
    val windowState: WindowState = rememberWindowState(size = DpSize(400.dp, 660.dp), position = WindowPosition(
        Alignment.BottomEnd))
    var isWindowVisible by remember { mutableStateOf(true); }
    val notification = rememberNotification("Notification", "Message from MyApp!")

    Tray(
        state = trayState,
        icon = painterResource(Res.drawable.icon),
        menu = {
            Item(
                "Open App",
                onClick = { isWindowVisible = true }
            )
            Item(
                "Send notification",
                onClick = { trayState.sendNotification(notification) }
            )
            Item(
                "Exit",
                onClick = ::exitApplication
            )
        },
        onAction = { isWindowVisible = true }
    )

    if (isWindowVisible)
        Window(
            state = windowState,
            title = "Nova Hub",
            resizable = false,
            undecorated = false,
            onCloseRequest = { isWindowVisible = false },
        ) { App() }

}
