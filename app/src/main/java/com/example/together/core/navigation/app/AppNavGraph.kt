package com.example.together.core.navigation.app

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.together.core.navigation.Routes
import com.example.together.feature.dashboard.home.HomeScreen

fun NavGraphBuilder.appNavGraph(
    navController: NavController,
    paddingValues: PaddingValues
) {
    composable<AppRoutes.HOME>() {
        HomeScreen(
            paddingValues = paddingValues,
            onLogout = {
                navController.navigate(Routes.AUTH_GRAPH) {
                    popUpTo(Routes.APP_GRAPH) {
                        inclusive = true
                    }
                }
            })
    }
}