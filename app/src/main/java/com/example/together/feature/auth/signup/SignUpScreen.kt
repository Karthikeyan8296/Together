package com.example.together.feature.auth.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Sign up",
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        letterSpacing = (-1).sp
    )
}