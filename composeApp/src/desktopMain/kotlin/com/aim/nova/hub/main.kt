package com.aim.nova.hub

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.*
import novahub.composeapp.generated.resources.Res
import novahub.composeapp.generated.resources.icon
import org.jetbrains.compose.resources.painterResource

fun main() = application {

    val trayState: TrayState = rememberTrayState()
    val windowState: WindowState = rememberWindowState(size = DpSize())
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
            title = "Nova Hub",
            onCloseRequest = { isWindowVisible = false },
        ) { App() }

}
