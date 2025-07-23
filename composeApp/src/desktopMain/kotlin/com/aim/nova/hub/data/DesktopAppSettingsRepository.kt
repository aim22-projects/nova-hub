// desktopMain/kotlin/com/example/myapp/data/DesktopAppSettingsRepository.kt
package com.aim.nova.hub.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import java.nio.file.Paths

class DesktopAppSettingsRepository : AppSettingsRepository {

    private val nodePathKey = stringPreferencesKey("node_path")

    // Create a DataStore instance for Desktop
    private val dataStore: DataStore<Preferences> = createDataStore(
//        producePath = {
//            // Define a suitable path for desktop, e.g., in user's application data directory
//            // For simplicity, using a temporary directory here.
//            // In a real app, consider a more persistent location like user home or app-specific data dir.
//            File(System.getProperty("java.io.tmpdir"), DATA_STORE_FILE_NAME).absolutePath
//        }
        producePath = {
            // Define a suitable and persistent path for desktop applications.
            // Using user.home and a hidden application-specific directory is common.
            val appDataDir = Paths.get(System.getProperty("user.home"), ".my_desktop_app", "data").toFile()
            if (!appDataDir.exists()) {
                appDataDir.mkdirs() // Create directories if they don't exist
            }
            File(appDataDir, DATA_STORE_FILE_NAME).absolutePath
        }
    )

    override fun getSettings(): Flow<AppSettings> = dataStore.data.map { preferences ->
        AppSettings(
            nodePath = preferences[nodePathKey] ?: ""
        )
    }

    override suspend fun updateNodePath(path: String) {
        dataStore.edit { preferences ->
            preferences[nodePathKey] = path
        }
    }
}
