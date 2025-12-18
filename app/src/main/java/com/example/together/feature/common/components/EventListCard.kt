package com.example.together.feature.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.together.feature.dashboard.home.EventUiModel
import com.example.together.ui.theme.backgroundPrimary
import com.example.together.ui.theme.backgroundSecondary
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white

@Composable
fun EventListCard(
    event: EventUiModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundSecondary)
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = event.coverImageUrl,
            contentDescription = event.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 96.dp, height = 72.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = event.name,
                color = white,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = event.category,
                color = light_white,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = event.location,
                color = light_white.copy(alpha = 0.8f),
                fontSize = 12.sp
            )
        }

        Spacer(Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(backgroundPrimary)
                .border(1.5.dp, primary, RoundedCornerShape(999.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Book",
                color = white,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
