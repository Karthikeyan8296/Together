package com.example.together.feature.auth.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.R
import com.example.together.feature.common.components.OnboardingSlide
import com.example.together.ui.theme.backgroundPrimary
import com.example.together.ui.theme.primary
import kotlinx.coroutines.launch

data class OnboardingPage(
    val title: String,
    val description: String,
    val lottieResId: Int
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    paddingValues: PaddingValues,
    onFinished: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(
            title = "Discover Events You’ll Love, All in One Place",
            description = "Browse curated experiences, explore trending activities, and find the perfect event for any moment",
            lottieResId = R.raw.media
        ),
        OnboardingPage(
            title = "Plan Effortlessly for your community",
            description = "Invite your group, coordinate schedules, and keep everyone aligned without the endless messaging",
            lottieResId = R.raw.discover
        ),
        OnboardingPage(
            title = "Stay Organized and in Control Every Step",
            description = "Track RSVPs, manage tickets, and sync everything to your calendar for a smooth event journey",
            lottieResId = R.raw.search
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )
    val scope = rememberCoroutineScope()
    val lastPageIndex = pages.lastIndex

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundPrimary)
            .padding(paddingValues)
    ) {

        Text(
            text = "Together",
            color = primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 12.dp),
            textAlign = TextAlign.Center
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { pageIndex ->
            val page = pages[pageIndex]

            OnboardingSlide(
                title = page.title,
                description = page.description,
                currentPageIndex = pagerState.currentPage,
                totalPages = pages.size,
                paddingValues = PaddingValues(0.dp), // header already applied
                lottieResId = page.lottieResId,
                onNext = {
                    if (pagerState.currentPage < lastPageIndex) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinished()
                    }
                }
            )
        }
    }
}