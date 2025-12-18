package com.example.together.feature.common.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.together.feature.dashboard.home.EventUiModel
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.white
import kotlin.math.absoluteValue
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventCarousel(
    events: List<EventUiModel>,
    modifier: Modifier = Modifier
) {
    if (events.isEmpty()) return

    val itemCount = events.size

    // Fake infinite pager by using a huge page count and starting near the middle
    val infinitePages = Int.MAX_VALUE
    val startPage = infinitePages / 2 - (infinitePages / 2 % itemCount)

    val pagerState = rememberPagerState(
        initialPage = startPage,
        pageCount = { infinitePages }
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp),
            pageSpacing = (-10).dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(310.dp)
        ) { page ->
            val index = page % itemCount

            // how far is this page from the center?
            val pageOffset = (
                    pagerState.currentPage - page +
                            pagerState.currentPageOffsetFraction
                    ).absoluteValue

            // scale between 0.9f and 1f
            val scale = lerp(
                start = 0.82f,
                stop = 1f,
                fraction = (1f - pageOffset.coerceIn(0f, 1f))
            )

            // alpha between 0.6f and 1f
            val alpha = lerp(
                start = 0.6f,
                stop = 1f,
                fraction = (1f - pageOffset.coerceIn(0f, 1f))
            )

            EventCarouselCard(
                event = events[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ------- Smooth moving indicator -------
        val indicatorCount = 5
        val currentIndicator = pagerState.currentPage % indicatorCount

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(indicatorCount) { index ->
                val distance = abs(index - currentIndicator)

                val targetSize = when (distance) {
                    0 -> 8.5.dp   // active – biggest
                    1 -> 7.dp   // neighbours – medium
                    else -> 5.dp // outer – small
                }

                val targetAlpha = when (distance) {
                    0 -> 1f
                    1 -> 0.7f
                    else -> 0.4f
                }

                val size by animateDpAsState(
                    targetValue = targetSize,
                    animationSpec = tween(durationMillis = 220),
                    label = "dotSize"
                )
                val alpha by animateFloatAsState(
                    targetValue = targetAlpha,
                    animationSpec = tween(durationMillis = 220),
                    label = "dotAlpha"
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(size)
                        .clip(CircleShape)
                        .background(
                            if (distance == 0)
                                white
                            else
                                light_white.copy(alpha = alpha)
                        )
                )
            }
        }
    }
}
