package com.example.together.feature.onboarding.OnboardingScreen2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen2(modifier: Modifier = Modifier) {
    Text(
        text = "Onboarding screen 2",
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        letterSpacing = (-1).sp
    )
}