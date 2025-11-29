package com.example.together.feature.onboarding.OnboardingScreen1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.together.ui.theme.white

@Composable
fun OnboardingScreen1(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Onboarding screen 1",
            fontWeight = FontWeight.SemiBold,
            color = white,
            fontSize = 24.sp,
            letterSpacing = (-1).sp
        )
    }
}