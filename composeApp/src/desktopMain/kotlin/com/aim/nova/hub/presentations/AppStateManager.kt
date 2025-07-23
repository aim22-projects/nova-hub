package com.aim.nova.hub.presentations

import com.aim.nova.hub.data.AppSettings
import com.aim.nova.hub.data.AppSettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// A simple ViewModel-like class for app-level state
class AppStateManager(
    private val appSettingsRepository: AppSettingsRepository
) {
    // Use a dedicated scope for app-level state management
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val _appSettings = MutableStateFlow(AppSettings())
    val appSettings: StateFlow<AppSettings> = _appSettings.asStateFlow()

    init {
        // Collect settings from the repository and update the StateFlow
        appSettingsRepository.getSettings()
            .onEach { settings ->
                _appSettings.value = settings
            }
            .launchIn(scope) // Launch a collection in the dedicated scope
    }

    fun updateNodePath(path: String) {
        scope.launch {
            appSettingsRepository.updateNodePath(path)
        }
    }

    // You might also expose individual settings directly for convenience
    val nodePath: StateFlow<String> = appSettings
        .map { it.nodePath }
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly, // Start collecting immediately and keep alive
            initialValue = appSettings.value.nodePath // Provide an initial value
        )
}