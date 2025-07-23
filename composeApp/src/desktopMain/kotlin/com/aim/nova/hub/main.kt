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
import com.aim.nova.hub.di.commonModule
import com.aim.nova.hub.di.desktopModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


fun main() = application {
    val windowState: WindowState = rememberWindowState(
        size = DpSize(400.dp, 660.dp),
        position = WindowPosition(Alignment.BottomEnd)
    )
    val trayState: TrayState = rememberTrayState()
    var isWindowVisible by remember { mutableStateOf(true) }

    startKoin {
        // Optional: Add a logger to see Koin logs in the console
        // For production, you might want to remove or configure this differently
//        logger(Slf4jLogger())

        // Load your Koin modules
        // commonModule contains AppStateManager
        // desktopModule contains DesktopAppSettingsRepository
        modules(commonModule, desktopModule)
    }

    // Optional: Add a shutdown hook to stop Koin when the application exits
    // This can help with resource cleanup, though for simple apps it might not be strictly necessary.
    Runtime.getRuntime().addShutdownHook(Thread {
        stopKoin()
        println("Koin stopped.")
    })


    AppTrayIcon( trayState ) { isWindowVisible = true }

    if (isWindowVisible)
        AppWindow(windowState, onClose = { isWindowVisible = false }){
            AppTheme {
                App(windowState,  onClose = { isWindowVisible = false })
            }
        }
}
