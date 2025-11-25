package com.example.together.core.navigation.onBoarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.together.feature.onboarding.OnboardingScreen1.OnboardingScreen1
import com.example.together.feature.onboarding.OnboardingScreen2.OnboardingScreen2

fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    composable<OnBoardingRoutes.ONBOARDING_1> {
        OnboardingScreen1()
    }

    composable<OnBoardingRoutes.ONBOARDING_2> {
        OnboardingScreen2()
    }
}