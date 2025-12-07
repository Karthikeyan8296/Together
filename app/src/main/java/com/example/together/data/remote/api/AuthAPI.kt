package com.example.together.data.remote.api

import com.example.together.data.remote.dto.LoginRequest
import com.example.together.data.remote.dto.LoginResponse
import com.example.together.data.remote.dto.LogoutRequest
import com.example.together.data.remote.dto.LogoutResponse
import com.example.together.data.remote.dto.OnboardingRequest
import com.example.together.data.remote.dto.OnboardingResponse
import com.example.together.data.remote.dto.RefreshRequest
import com.example.together.data.remote.dto.RefreshResponse
import com.example.together.data.remote.dto.SendOtpRequest
import com.example.together.data.remote.dto.SendOtpResponse
import com.example.together.data.remote.dto.SignUpRequest
import com.example.together.data.remote.dto.SignUpResponse
import com.example.together.data.remote.dto.VerifyEmailRequest
import com.example.together.data.remote.dto.VerifyEmailResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth/send-otp")
    suspend fun sendOtp(
        @Body body: SendOtpRequest
    ): SendOtpResponse

    @POST("auth/verify-email")
    suspend fun verifyEmail(
        @Body body: VerifyEmailRequest
    ): VerifyEmailResponse

    @POST("auth/sign-up")
    suspend fun signUp(
        @Body body: SignUpRequest
    ): SignUpResponse

    @POST("auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    @POST("auth/logout")
    suspend fun logout(
        @Body body: LogoutRequest
    ): LogoutResponse

    @POST("user/onBoarding")
    suspend fun createOnBoarding(
        @Body body: OnboardingRequest
    ): OnboardingResponse
}