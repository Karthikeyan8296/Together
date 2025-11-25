package com.example.together.core.navigation.auth

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRoutes {
    @Serializable
    data object WELCOME_ONBOARDING : AuthRoutes

    @Serializable
    data object GET_STARTED : AuthRoutes

    @Serializable
    data object LOGIN_SCREEN : AuthRoutes

    @Serializable
    data object SIGNUP_SCREEN : AuthRoutes

}