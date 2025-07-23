// commonMain/kotlin/com/example/myapp/di/AppModule.kt
package com.aim.nova.hub.di

import com.aim.nova.hub.data.AppSettingsRepository
import com.aim.nova.hub.data.DesktopAppSettingsRepository
import com.aim.nova.hub.presentations.AppStateManager
import org.koin.dsl.module

val commonModule = module {
    // AppSettingsRepository will be provided by platform-specific modules
    single { AppStateManager(get()) }
}

val desktopModule = module {
    single<AppSettingsRepository> { DesktopAppSettingsRepository() }
}
