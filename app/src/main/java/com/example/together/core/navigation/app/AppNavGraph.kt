package com.example.together.core.navigation.app

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.together.core.navigation.NavTransitions.scaleInFromCenter
import com.example.together.core.navigation.NavTransitions.scaleOutToCenter
import com.example.together.core.navigation.NavTransitions.slideInFromRight
import com.example.together.core.navigation.NavTransitions.slideOutToRight
import com.example.together.core.navigation.Routes
import com.example.together.feature.dashboard.home.HomeScreen
import com.example.together.feature.dashboard.profile.ProfileScreen
import com.example.together.feature.dashboard.search.SearchScreen

fun NavGraphBuilder.appNavGraph(
    navController: NavController,
    paddingValues: PaddingValues
) {
    composable<AppRoutes.HOME>(
        exitTransition = scaleOutToCenter,
        popEnterTransition = scaleInFromCenter,
        enterTransition = slideInFromRight,
        popExitTransition = slideOutToRight
    ) {
        HomeScreen(
            paddingValues = paddingValues,
            onLogout = {
                navController.navigate(Routes.AUTH_GRAPH) {
                    popUpTo(Routes.APP_GRAPH) {
                        inclusive = true
                    }
                }
            },
            onSearchClick = { navController.navigate(AppRoutes.SEARCH) },
            onProfileClick = { navController.navigate(AppRoutes.PROFILE) }
        )
    }

    composable<AppRoutes.SEARCH>(
        exitTransition = scaleOutToCenter,
        popEnterTransition = scaleInFromCenter,
        enterTransition = slideInFromRight,
        popExitTransition = slideOutToRight
    ) {
        SearchScreen(paddingValues = paddingValues)
    }

    composable<AppRoutes.PROFILE>(
        exitTransition = scaleOutToCenter,
        popEnterTransition = scaleInFromCenter,
        enterTransition = slideInFromRight,
        popExitTransition = slideOutToRight
    ) {
        ProfileScreen(paddingValues = paddingValues)
    }
}