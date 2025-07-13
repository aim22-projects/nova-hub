package com.aim.nova.hub

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberNotification
import androidx.compose.ui.window.rememberTrayState


fun main() = application {
    var action by remember { mutableStateOf("Last action: None") }
    val trayState = rememberTrayState()
    val notification = rememberNotification("Notification", "Message from MyApp!")
    var isSubmenuShowing by remember { mutableStateOf(false) }
    var isOpen by remember { mutableStateOf(true) }

    Tray(
        state = trayState,
        icon = TrayIcon,
        menu = {
            Item(
                "Increment value",
                onClick = {
//                    count++
                }
            )
            Item(
                "Send notification",
                onClick = {
                    trayState.sendNotification(notification)
                }
            )
            Item(
                "Exit",
                onClick = {
//                    isOpen = false
                }
            )
        }
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "Nova Hub",
    ) {
        MenuBar {
            Menu("File", mnemonic = 'F') {
                Item("Copy", onClick = { action = "Last action: Copy" }, shortcut = KeyShortcut(Key.C, ctrl = true))
                Item(
                    "Paste",
                    onClick = { action = "Last action: Paste" },
                    shortcut = KeyShortcut(Key.V, ctrl = true)
                )
            }
            Menu("Actions", mnemonic = 'A') {
                CheckboxItem(
                    "Advanced settings",
                    checked = isSubmenuShowing,
                    onCheckedChange = {
                        isSubmenuShowing = !isSubmenuShowing
                    }
                )
                if (isSubmenuShowing) {
                    Menu("Settings") {
                        Item("Setting 1", onClick = { action = "Last action: Setting 1" })
                        Item("Setting 2", onClick = { action = "Last action: Setting 2" })
                    }
                }
                Separator()
                Item("About", icon = AboutIcon, onClick = { action = "Last action: About" })
                Item("Exit", onClick = { isOpen = false }, shortcut = KeyShortcut(Key.Escape), mnemonic = 'E')
            }
        }
        App()
    }

}

object TrayIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFA500))
    }
}

object AboutIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFA500))
    }
}