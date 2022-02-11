package com.example.gymappandroid.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_session")
        val SESSION_KEY = stringPreferencesKey("user_session")
    }

    val getUserSession: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[SESSION_KEY] ?: "something bad happened"
        }

    suspend fun saveUserSession(uid: String) {
        context.dataStore.edit { preferences ->
            preferences[SESSION_KEY] = uid
        }
    }
}