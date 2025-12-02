package com.example.together.core.navigation.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.together.core.navigation.NavTransitions
import com.example.together.core.navigation.Routes
import com.example.together.feature.auth.login.LoginScreen
import com.example.together.feature.auth.signup.SignUpScreen
import com.example.together.feature.auth.welcome.GetStartedScreen
import com.example.together.feature.auth.welcome.OnboardingScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    composable<AuthRoutes.WELCOME_ONBOARDING>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter
    ) {
        OnboardingScreen(
            paddingValues = paddingValues,
            onFinished = {
                navController.navigate(AuthRoutes.GET_STARTED)
            }
        )
    }

    composable<AuthRoutes.GET_STARTED>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter,
        enterTransition = NavTransitions.slideInFromRight,
        popExitTransition = NavTransitions.slideOutToRight
    ) {
        GetStartedScreen(
            paddingValues = paddingValues,
            onSignUpWithEmail = { navController.navigate(AuthRoutes.SIGNUP_SCREEN) },
            onLoginClick = { navController.navigate(AuthRoutes.LOGIN_SCREEN) }
        )
    }

    composable<AuthRoutes.LOGIN_SCREEN>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter,
        enterTransition = NavTransitions.slideInFromRight,
        popExitTransition = NavTransitions.slideOutToRight
    ) {
        LoginScreen(
            paddingValues = paddingValues,
            onLoginComplete = { navController.navigate(Routes.ONBOARDING_GRAPH) }
        )
    }

    composable<AuthRoutes.SIGNUP_SCREEN>(
        exitTransition = NavTransitions.scaleOutToCenter,
        popEnterTransition = NavTransitions.scaleInFromCenter,
        enterTransition = NavTransitions.slideInFromRight,
        popExitTransition = NavTransitions.slideOutToRight
    ) {
        SignUpScreen(
            paddingValues = paddingValues,
            onSignInComplete = { navController.navigate(Routes.ONBOARDING_GRAPH) }
        )
    }
}