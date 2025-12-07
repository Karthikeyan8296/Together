package com.example.together.core.navigation.onBoarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.together.core.navigation.NavTransitions
import com.example.together.feature.onboarding.OnboardingScreen1
import com.example.together.feature.onboarding.OnboardingScreen2
import com.example.together.feature.onboarding.OnboardingScreen3
import com.example.together.feature.onboarding.OnboardingViewModel

fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    composable<OnBoardingRoutes.ONBOARDING_1>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter,
        enterTransition = NavTransitions.slideInFromRight,
        popExitTransition = NavTransitions.slideOutToRight
    ) { backStackEntry ->
        val onBoardingViewModel: OnboardingViewModel = hiltViewModel(backStackEntry)
        OnboardingScreen1(
            paddingValues = paddingValues,
            viewModal = onBoardingViewModel,
            handleContinue = { navController.navigate(OnBoardingRoutes.ONBOARDING_2) }
        )
    }

    composable<OnBoardingRoutes.ONBOARDING_2>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter,
        enterTransition = NavTransitions.slideInFromRight,
        popExitTransition = NavTransitions.slideOutToRight
    ) { backStackEntry ->
        //Reuse SAME ViewModel from ONBOARDING_1
        val parentEntry = remember {
            navController.getBackStackEntry(OnBoardingRoutes.ONBOARDING_1)
        }
        val onboardingViewModel: OnboardingViewModel = hiltViewModel(parentEntry)
        OnboardingScreen2(
            paddingValues = paddingValues,
            viewModal = onboardingViewModel,
            handleContinue = { navController.navigate(OnBoardingRoutes.ONBOARDING_3) })
    }

    composable<OnBoardingRoutes.ONBOARDING_3>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter,
        enterTransition = NavTransitions.slideInFromRight,
        popExitTransition = NavTransitions.slideOutToRight
    ) {
        // Same shared ViewModel again
        val parentEntry = remember {
            navController.getBackStackEntry(OnBoardingRoutes.ONBOARDING_1)
        }
        val onboardingViewModel: OnboardingViewModel =
            hiltViewModel(parentEntry)
        OnboardingScreen3(
            paddingValues = paddingValues,
            navController = navController,
            viewModal = onboardingViewModel
        )
    }
}