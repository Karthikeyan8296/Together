package com.example.together.core.navigation.onBoarding

import kotlinx.serialization.Serializable

@Serializable
sealed interface OnBoardingRoutes {
    @Serializable
    data object ONBOARDING_1: OnBoardingRoutes

    @Serializable
    data object ONBOARDING_2: OnBoardingRoutes

    @Serializable
    data object ONBOARDING_3: OnBoardingRoutes
}