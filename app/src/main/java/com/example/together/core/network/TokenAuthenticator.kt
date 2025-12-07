package com.example.together.core.network

import com.example.together.data.local.dataStore.AuthDataStore
import com.example.together.data.remote.api.TokenAPI
import com.example.together.data.remote.dto.RefreshRequest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenAPI: TokenAPI,
    private val authDataStore: AuthDataStore
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        //Avoid infinite loops: if we already tried with a new token, give up
        if (responseCount(response) >= 2) {
            return null
        }

        //Don't try to refresh for auth endpoints themselves
        val path = response.request.url.encodedPath
        if (path.startsWith("/auth")) {
            return null
        }

        return runBlocking {
            val tokens = authDataStore.authTokens.first()
            val refreshToken = tokens.refreshToken ?: return@runBlocking null

            try {
                val res = tokenAPI.refresh(RefreshRequest(refreshToken))

                authDataStore.saveTokens(
                    accessToken = res.accessToken,
                    refreshToken = res.refreshToken,
                    email = tokens.email ?: "",
                    id = tokens.id ?: ""
                )

                response.request.newBuilder()
                    .header("Authorization", "Bearer ${res.accessToken}")
                    .build()

            } catch (e: Exception) {
                authDataStore.clearTokens()
                null
            }
        }
    }

    private fun responseCount(response: Response): Int {
        var count = 1
        var current = response.priorResponse
        while (current != null) {
            count++
            current = current.priorResponse
        }
        return count
    }
}