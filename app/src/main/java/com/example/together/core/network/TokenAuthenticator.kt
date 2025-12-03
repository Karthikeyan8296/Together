package com.example.together.core.network

import com.example.together.data.local.dataStore.AuthDataStore
import com.example.together.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val authRepository: AuthRepository,
    private val authDataStore: AuthDataStore
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        //Avoid infinite loops: if we already tried with a new token, give up
        if (responseCount(response) >= 2) {
            return null
        }

        //Don't try to refresh for auth endpoints themselves
        val path = response.request.url.encodedPath
        if (path.startsWith("/api/v1/auth")) {
            return null
        }

        return runBlocking {
            val tokens = authDataStore.authTokens.first()
            val refreshToken = tokens.refreshToken

            if (refreshToken.isNullOrBlank()) {
                // No refresh token -> can't do anything
                authDataStore.clearTokens()
                return@runBlocking null
            }

            //try refresh
            val result = authRepository.refreshToken(refreshToken)

            val newAccessToken = result.getOrElse {
                // Refresh failed -> clear tokens, user must log in again
                authDataStore.clearTokens()
                return@runBlocking null
            }.accessToken

            // Build new request with new access token
            response.request.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
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