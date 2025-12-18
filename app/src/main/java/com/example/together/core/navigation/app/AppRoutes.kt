package com.example.together.core.navigation.app

import kotlinx.serialization.Serializable

interface AppRoutes {

    @Serializable
    data object HOME : AppRoutes

    @Serializable
    data object SEARCH : AppRoutes

    @Serializable
    data object PROFILE : AppRoutes
}