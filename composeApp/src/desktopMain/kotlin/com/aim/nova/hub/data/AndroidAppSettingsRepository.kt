//// androidMain/kotlin/com/example/myapp/data/AndroidAppSettingsRepository.kt
//package com.aim.nova.hub.data
//
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.core.stringPreferencesKey
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class AndroidAppSettingsRepository(private val context: Context) : AppSettingsRepository {
//
//    private val NODE_PATH_KEY = stringPreferencesKey("node_path")
//
//    // Create a DataStore instance using the common factory
//    private val dataStore: DataStore<Preferences> = createDataStore(
//        producePath = { context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath }
//    )
//
//    override fun getSettings(): Flow<AppSettings> {
//        return dataStore.data.map { preferences ->
//            AppSettings(
//                nodePath = preferences[NODE_PATH_KEY] ?: ""
//            )
//        }
//    }
//
//    override suspend fun updateNodePath(path: String) {
//        dataStore.edit { preferences ->
//            preferences[NODE_PATH_KEY] = path
//        }
//    }
//}