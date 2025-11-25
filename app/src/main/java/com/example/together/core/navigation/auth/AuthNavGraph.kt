package com.example.together.core.navigation.auth

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.together.feature.auth.login.LoginScreen
import com.example.together.feature.auth.signup.SignUpScreen
import com.example.together.feature.auth.welcome.GetStartedScreen
import com.example.together.feature.auth.welcome.OnboardingScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    composable<AuthRoutes.WELCOME_ONBOARDING>(
        exitTransition = {
            // When navigating *away* from Onboarding (→ GetStarted)
            scaleOut(
                targetScale = 0.5f,
                animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))
        },
        popEnterTransition = {
            // When coming *back* to Onboarding (← GetStarted)
            scaleIn(
                initialScale = 0.5f,
                animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500))
        }

    ) {
        OnboardingScreen(
            paddingValues = paddingValues,
            onFinished = {
                navController.navigate(AuthRoutes.GET_STARTED)
            }
        )
    }

    composable<AuthRoutes.GET_STARTED>(
        enterTransition = {
            // When navigating *to* GetStarted (from Onboarding)
            scaleIn(
                initialScale = 0.5f,
                animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500))
        },
        popExitTransition = {
            // When popping back from GetStarted (→ Onboarding)
            scaleOut(
                targetScale = 0.5f,
                animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))
        }
    ) {
        GetStartedScreen(
            paddingValues = paddingValues,
            onSignUpWithEmail = { navController.navigate(AuthRoutes.SIGNUP_SCREEN) },
            onLoginClick = { navController.navigate(AuthRoutes.LOGIN_SCREEN) }
        )
    }

    composable<AuthRoutes.LOGIN_SCREEN> {
        LoginScreen()
    }

    composable<AuthRoutes.SIGNUP_SCREEN> {
        SignUpScreen()
    }
}