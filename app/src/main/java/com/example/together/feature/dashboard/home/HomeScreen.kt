package com.example.together.feature.dashboard.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.together.R
import com.example.together.feature.common.components.EventCarousel
import com.example.together.feature.common.components.EventListCard
import com.example.together.feature.common.components.HomeCategoryTab
import com.example.together.feature.common.components.HomeCategoryTabRow
import com.example.together.feature.common.components.HomeHeader
import com.example.together.feature.common.components.SearchBar
import com.example.together.feature.common.components.SectionDividerTitle

data class EventUiModel(
    val id: String,
    val name: String,
    val coverImageUrl: String,
    val category: String,
    val location: String,
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onSearchClick: () -> Unit,
    viewModal: HomeScreenViewModel = hiltViewModel(),
    onProfileClick: () -> Unit
) {
    val state by viewModal.uiState.collectAsStateWithLifecycle()
    val tabs = listOf(
        HomeCategoryTab("for_you", "For You", R.drawable.for_you),
        HomeCategoryTab("tech", "Tech", R.drawable.code),
        HomeCategoryTab("design", "Design", R.drawable.design),
        HomeCategoryTab("startup", "Startup", R.drawable.start_up),
        HomeCategoryTab("business", "Business", R.drawable.business),
    )
    var selectedTabId by remember { mutableStateOf(tabs.first().id) }

    val mockRecommendedEvents = listOf(
        EventUiModel(
            id = "1",
            name = "Design Hackathon",
            coverImageUrl = "https://picsum.photos/seed/design/600/400",
            category = "Design • Hackathon",
            location = "Chennai, India",
        ),
        EventUiModel(
            id = "2",
            name = "Startup Pitch Fest",
            coverImageUrl = "https://picsum.photos/seed/startup/600/400",
            category = "Business • Startup",
            location = "Hyderabad, India",
        ),
        EventUiModel(
            id = "3",
            name = "Music Fusion Night",
            coverImageUrl = "https://picsum.photos/seed/music/600/400",
            category = "Music • Live",
            location = "Chennai, India",
        ),
        EventUiModel(
            id = "4",
            name = "Health & Wellness Expo",
            coverImageUrl = "https://picsum.photos/seed/health/600/400",
            category = "Wellness • Expo",
            location = "Pune, India",
        )
    )

    val mockEvents = listOf(
        EventUiModel(
            id = "1",
            name = "Design Hackathon",
            coverImageUrl = "https://picsum.photos/seed/design/600/400",
            category = "Design • Hackathon",
            location = "Chennai, India",
        ),
        EventUiModel(
            id = "2",
            name = "Startup Pitch Fest",
            coverImageUrl = "https://picsum.photos/seed/startup/600/400",
            category = "Business • Startup",
            location = "Hyderabad, India",
        ),
        EventUiModel(
            id = "3",
            name = "Music Fusion Night",
            coverImageUrl = "https://picsum.photos/seed/music/600/400",
            category = "Music • Live",
            location = "Chennai, India",
        ),
        EventUiModel(
            id = "4",
            name = "Health & Wellness Expo",
            coverImageUrl = "https://picsum.photos/seed/health/600/400",
            category = "Wellness • Expo",
            location = "Pune, India",
        ),
        EventUiModel(
            id = "5",
            name = "Health & Wellness Expo",
            coverImageUrl = "https://picsum.photos/seed/tech/600/400",
            category = "Wellness • Expo",
            location = "Pune, India",
        ),
        EventUiModel(
            id = "6",
            name = "Health & Wellness Expo",
            coverImageUrl = "https://picsum.photos/seed/market/600/400",
            category = "Wellness • Expo",
            location = "Pune, India",
        ),
        EventUiModel(
            id = "7",
            name = "Health & Wellness Expo",
            coverImageUrl = "https://picsum.photos/seed/finance/600/400",
            category = "Wellness • Expo",
            location = "Pune, India",
        ),
        EventUiModel(
            id = "8",
            name = "Health & Wellness Expo",
            coverImageUrl = "https://picsum.photos/seed/city/600/400",
            category = "Wellness • Expo",
            location = "Pune, India",
        ),
        EventUiModel(
            id = "9",
            name = "Health & Wellness Expo",
            coverImageUrl = "https://picsum.photos/seed/music/600/400",
            category = "Wellness • Expo",
            location = "Pune, India",
        )
    )

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        state = listState,
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            //Header
            HomeHeader(
                name = "Karthikeyan",
                location = "Chennai, India",
                onProfileClick = onProfileClick
            )

            //search field
            SearchBar(
                onClick = { onSearchClick() },
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            HomeCategoryTabRow(
                tabs = tabs,
                selectedId = selectedTabId,
                onTabSelected = { tab ->
                    selectedTabId = tab.id
                    // TODO: filter your event list based on tab.id
                }
            )

            Spacer(modifier = Modifier.height(2.dp))

            //Section 1
            SectionDividerTitle(text = "RECOMMENDED FOR YOU")
            EventCarousel(
                events = mockRecommendedEvents,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            //Section 1
            SectionDividerTitle(text = "UPCOMING EVENTS")

            Spacer(modifier = Modifier.height(5.dp))
        }
        items(
            items = mockEvents,
            key = { it.id }
        ) { event ->
            EventListCard(
                event = event,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                onClick = {}
            )
        }
    }
}

