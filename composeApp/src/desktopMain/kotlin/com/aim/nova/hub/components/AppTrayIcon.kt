package com.aim.nova.hub.components;

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.window.*
import novahub.composeapp.generated.resources.Res
import novahub.composeapp.generated.resources.icon
import org.jetbrains.compose.resources.painterResource

/**
 * Note: Tray is part of the application lifecycle scope so we cannot use it separate component
 * even if declare it inside application {...}, in the separate component.
 * Build is no longer working i.e., build is completing
 * */

@Composable
fun AppTrayIcon(
    trayState: TrayState,
    onExit: () -> Unit,
    onShowWindow: () -> Unit
) {
    val notification = rememberNotification("Notification", "Message from MyApp!")

    application {
        Tray(
            state = trayState,
            icon = painterResource(Res.drawable.icon),
            menu = {
//                Item(
//                    "Increment value",
//                    onClick = {
//                    count++
//                    }
//                )
                Item(
                    "Send notification",
                    onClick = {
                        trayState.sendNotification(notification)
                    }
                )
                Item(
                    "Exit",
                    onClick = {
                        onExit
                    }
                )
            },
            onAction = {
                onShowWindow
            }

        )

    }
}