package com.example.together.feature.auth.login

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Login in",
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        letterSpacing = (-1).sp
    )
}