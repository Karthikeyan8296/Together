package com.example.together.core.navigation.onBoarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.together.core.navigation.NavTransitions
import com.example.together.feature.onboarding.OnboardingScreen1.OnboardingScreen1
import com.example.together.feature.onboarding.OnboardingScreen2.OnboardingScreen2
import com.example.together.feature.onboarding.OnboardingScreen3.OnboardingScreen3

fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    composable<OnBoardingRoutes.ONBOARDING_1>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter,
        enterTransition = NavTransitions.slideInFromRight,
        popExitTransition = NavTransitions.slideOutToRight
    ) {
        OnboardingScreen1(
            paddingValues = paddingValues,
            handleContinue = { navController.navigate(OnBoardingRoutes.ONBOARDING_2) }
        )
    }

    composable<OnBoardingRoutes.ONBOARDING_2>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter,
        enterTransition = NavTransitions.slideInFromRight,
        popExitTransition = NavTransitions.slideOutToRight
    ) {
        OnboardingScreen2(
            paddingValues = paddingValues,
            handleContinue = { navController.navigate(OnBoardingRoutes.ONBOARDING_3) })
    }

    composable<OnBoardingRoutes.ONBOARDING_3>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter,
        enterTransition = NavTransitions.slideInFromRight,
        popExitTransition = NavTransitions.slideOutToRight
    ) {
        OnboardingScreen3(
            paddingValues = paddingValues,
            handleContinue = {})
    }
}