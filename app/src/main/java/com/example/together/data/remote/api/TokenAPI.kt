package com.example.together.data.remote.api

import com.example.together.data.remote.dto.RefreshRequest
import com.example.together.data.remote.dto.RefreshResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenAPI {
    @POST("auth/refresh")
    suspend fun refresh(@Body body: RefreshRequest): RefreshResponse
}