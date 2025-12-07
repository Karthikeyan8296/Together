package com.example.together.feature.common.states

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.together.ui.theme.backgroundSecondary
import com.example.together.ui.theme.primary

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LoadingAnimation(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularWavyProgressIndicator(color = primary, trackColor = backgroundSecondary)
    }
}
