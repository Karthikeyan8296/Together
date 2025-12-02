package com.example.together.data.remote.dto

data class UserDto(
    val id: String,
    val email: String,
)

data class SendOtpRequest(
    val email: String
)

data class SendOtpResponse(
    val message: String
)

data class VerifyEmailRequest(
    val email: String,
    val otp: String
)

data class VerifyEmailResponse(
    val isVerified: Boolean,
    val message: String,
    val signupToken: String
)

data class SignUpRequest(
    val signupToken: String,
    val password: String,
)

data class SignUpResponse(
    val message: String,
    val user: UserDto,
    val accessToken: String,
    val refreshToken: String,
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val message: String,
    val user: UserDto,
    val accessToken: String,
    val refreshToken: String,
    val isOnboarded: Boolean
)
