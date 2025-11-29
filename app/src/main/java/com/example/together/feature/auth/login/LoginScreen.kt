package com.example.together.feature.auth.login

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
fun LoginScreen(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Login",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = white,
            letterSpacing = (-1).sp
        )
    }
}