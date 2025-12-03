package com.example.together.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.together.core.navigation.app.AppRoutes
import com.example.together.core.navigation.app.appNavGraph
import com.example.together.core.navigation.auth.AuthRoutes
import com.example.together.core.navigation.auth.authNavGraph
import com.example.together.core.navigation.onBoarding.OnBoardingRoutes
import com.example.together.core.navigation.onBoarding.onBoardingNavGraph

@Composable
fun RootNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    startDestination: Routes
) {
    NavHost(navController = navController, startDestination = startDestination) {
        //AUTH GRAPH
        navigation<Routes.AUTH_GRAPH>(
            startDestination = AuthRoutes.WELCOME_ONBOARDING
        ) {
            authNavGraph(
                navController = navController,
                paddingValues = paddingValues
            )
        }

        //ONBOARDING GRAPH
        navigation<Routes.ONBOARDING_GRAPH>(
            startDestination = OnBoardingRoutes.ONBOARDING_1
        ) {
            onBoardingNavGraph(
                navController = navController,
                paddingValues = paddingValues
            )
        }

        //APP GRAPH
        navigation<Routes.APP_GRAPH>(
            startDestination = AppRoutes.HOME
        ) {
            appNavGraph(
                navController = navController,
                paddingValues = paddingValues
            )
        }
    }
}