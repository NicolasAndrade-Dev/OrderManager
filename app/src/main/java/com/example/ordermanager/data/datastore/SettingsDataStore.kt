package com.example.ordermanager.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "settings"
)

class SettingsDataStore(
    private val context: Context
) {

    companion object {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
    }

    val darkTheme = context.dataStore.data.map { preferences ->
        preferences[DARK_THEME] ?: false
    }

    suspend fun saveDarkTheme(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME] = enabled
        }
    }
}