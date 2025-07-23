package com.aim.nova.hub.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okio.Path.Companion.toPath

// Define the filename for your DataStore preferences
internal const val DATA_STORE_FILE_NAME = "app_settings.preferences_pb"

// Factory function to create a DataStore instance
fun createDataStore(
    producePath: () -> String,
    corruptionHandler: androidx.datastore.core.handlers.ReplaceFileCorruptionHandler<Preferences>? = null,
    migrations: List<androidx.datastore.core.DataMigration<Preferences>> = emptyList(),
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    corruptionHandler = corruptionHandler,
    migrations = migrations,
    scope = scope,
    produceFile = { producePath().toPath() }
)