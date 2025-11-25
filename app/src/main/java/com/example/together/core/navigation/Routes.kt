package com.example.together.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    data object AUTH_GRAPH: Routes

    @Serializable
    data object ONBOARDING_GRAPH: Routes

    @Serializable
    data object APP_GRAPH: Routes
}