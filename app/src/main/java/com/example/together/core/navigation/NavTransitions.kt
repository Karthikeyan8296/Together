package com.example.together.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

object NavTransitions {

    private const val NAV_ANIM_DURATION = 350

    // Onboarding <-> GetStarted (scale + fade)
    val scaleOutToCenter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        scaleOut(
            targetScale = 0.3f,
            animationSpec = tween(NAV_ANIM_DURATION)
        ) + fadeOut(animationSpec = tween(NAV_ANIM_DURATION))
    }

    val scaleInFromCenter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            scaleIn(
                initialScale = 0.3f,
                animationSpec = tween(NAV_ANIM_DURATION)
            ) + fadeIn(animationSpec = tween(NAV_ANIM_DURATION))
        }

    // Generic slide-from-right pair
    val slideInFromRight: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(NAV_ANIM_DURATION)
            ) + fadeIn(animationSpec = tween(NAV_ANIM_DURATION))
        }

    val slideOutToRight: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(NAV_ANIM_DURATION)
        ) + fadeOut(animationSpec = tween(NAV_ANIM_DURATION))
    }
}
