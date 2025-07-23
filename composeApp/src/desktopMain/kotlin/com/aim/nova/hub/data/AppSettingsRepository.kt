package com.aim.nova.hub.data

import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {
    fun getSettings(): Flow<AppSettings>
    suspend fun updateNodePath(path: String)
    // Add other settings update functions here
}