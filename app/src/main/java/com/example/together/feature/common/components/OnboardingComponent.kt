package com.example.together.feature.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.together.R
import com.example.together.ui.theme.backgroundPrimary
import com.example.together.ui.theme.backgroundSecondary
import com.example.together.ui.theme.primary

@Composable
fun OnboardingSlide(
    title: String,
    description: String,
    currentPageIndex: Int,
    totalPages: Int,
    paddingValues: PaddingValues,
    lottieResId: Int,
    onNext: () -> Unit
) {
    val scrollState = rememberScrollState()
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(lottieResId))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundPrimary)
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
                LottieAnimation(
                    composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(400.dp)
                )
            }
            BottomCard(
                title = title,
                description = description,
                currentPageIndex = currentPageIndex,
                totalPages = totalPages,
                onNext = onNext
            )
        }
    }
}

@Composable
private fun BottomCard(
    title: String,
    description: String,
    currentPageIndex: Int,
    totalPages: Int,
    onNext: () -> Unit
) {
    val cardColor = backgroundSecondary
    val pillColor = primary

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(cardColor)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 30.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            color = Color(0xFFB5BAC8),
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(62.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // page indicator
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(totalPages) { index ->
                    Box(
                        modifier = Modifier
                            .height(6.dp)
                            .width(if (index == currentPageIndex) 24.dp else 10.dp)
                            .clip(RoundedCornerShape(999.dp))
                            .background(
                                if (index == currentPageIndex)
                                    pillColor
                                else
                                    Color(0xFF40424B)
                            )
                    )
                }
            }

            IconButton(
                onClick = onNext,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = pillColor)
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.chevron_right),
                    contentDescription = "Next",
                    tint = Color.Black
                )
            }
        }
    }
}
