package com.example.together.data.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val AUTH_DATASTORE_VALUE = "Auth_prefs"

private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(
    name = AUTH_DATASTORE_VALUE
)

data class AuthTokens(
    val accessToken: String?,
    val refreshToken: String?,
    val email: String?,
    val id: String?
) {
    val isLoggedIn: Boolean get() = !accessToken.isNullOrBlank() && !refreshToken.isNullOrBlank()
}

@Singleton
class AuthDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object Keys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val EMAIL = stringPreferencesKey("email")
        val ID = stringPreferencesKey("id")
    }

    val authTokens: Flow<AuthTokens> = context.authDataStore.data.map { pref ->
        AuthTokens(
            accessToken = pref[Keys.ACCESS_TOKEN],
            refreshToken = pref[Keys.REFRESH_TOKEN],
            email = pref[Keys.EMAIL],
            id = pref[Keys.ID]
        )
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String, email: String, id: String) {
        context.authDataStore.edit { pref ->
            pref[Keys.ACCESS_TOKEN] = accessToken
            pref[Keys.REFRESH_TOKEN] = refreshToken
            pref[Keys.EMAIL] = email
            pref[Keys.ID] = id
        }
    }

    suspend fun clearTokens() {
        context.authDataStore.edit { pref ->
            pref.remove(Keys.ACCESS_TOKEN)
            pref.remove(Keys.REFRESH_TOKEN)
            pref.remove(Keys.EMAIL)
            pref.remove(Keys.ID)
        }
    }
}