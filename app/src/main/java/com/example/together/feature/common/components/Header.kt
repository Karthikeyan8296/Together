package com.example.together.feature.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white

@Composable
fun Header(modifier: Modifier = Modifier, title: String, subTitle: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = primary)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            subTitle,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = light_white,
            lineHeight = 20.sp
        )
    }
    Spacer(Modifier.height(40.dp))
}