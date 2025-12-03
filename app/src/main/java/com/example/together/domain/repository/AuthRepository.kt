package com.example.together.domain.repository

import com.example.together.data.remote.dto.LoginResponse
import com.example.together.data.remote.dto.RefreshResponse
import com.example.together.data.remote.dto.SignUpResponse

interface AuthRepository {
    suspend fun sendOtp(email: String): Result<Unit>
    suspend fun verifyEmail(email: String, otp: String): Result<String>
    suspend fun signUp(signupToken: String, password: String): Result<SignUpResponse>
    suspend fun login(email: String, password: String): Result<LoginResponse>
    suspend fun logout(refreshToken: String): Result<Unit>
    suspend fun refreshToken(refreshToken: String): Result<RefreshResponse>
}