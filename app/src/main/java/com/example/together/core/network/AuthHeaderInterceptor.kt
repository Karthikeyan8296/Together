package com.example.together.core.network

import com.example.together.data.local.dataStore.AuthDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthHeaderInterceptor @Inject constructor(
    private val authDataStore: AuthDataStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        //Don't add auth header to the auth endpoints
        val path = original.url.encodedPath
        if (path.startsWith("/auth")) {
            return chain.proceed(original)
        }

        val accessToken = runBlocking {
            authDataStore.authTokens.first().accessToken
        }

        val newRequest = if (!accessToken.isNullOrBlank()) {
            original.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        } else {
            original
        }

        return chain.proceed(newRequest)
    }
}