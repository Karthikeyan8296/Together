package com.example.together.data.repository

import com.example.together.data.remote.api.AuthAPI
import com.example.together.data.remote.dto.LoginRequest
import com.example.together.data.remote.dto.LoginResponse
import com.example.together.data.remote.dto.SendOtpRequest
import com.example.together.data.remote.dto.SignUpRequest
import com.example.together.data.remote.dto.SignUpResponse
import com.example.together.data.remote.dto.VerifyEmailRequest
import com.example.together.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val API: AuthAPI
): AuthRepository {
    //returns nothing (useless)
    override suspend fun sendOtp(email: String): Result<Unit> = runCatching {
        API.sendOtp(SendOtpRequest(email = email))
        Unit
    }

    //returns string - signUpToken
    override suspend fun verifyEmail(
        email: String,
        otp: String
    ): Result<String> = runCatching {
        val res = API.verifyEmail(VerifyEmailRequest(email = email, otp = otp))
        if(!res.isVerified) error("Email is not verified")
        res.signupToken
    }

    //Returns values
    override suspend fun signUp(
        signupToken: String,
        password: String
    ): Result<SignUpResponse> = runCatching {
        API.signUp(SignUpRequest(signupToken = signupToken, password = password))
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse> = runCatching {
        API.login(LoginRequest(email = email, password = password))
    }

}