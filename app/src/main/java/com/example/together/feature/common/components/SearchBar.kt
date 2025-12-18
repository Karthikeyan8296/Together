package com.example.together.feature.common.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.ui.theme.backgroundSecondary
import com.example.together.ui.theme.iconPrimary
import com.example.together.ui.theme.light_white
import kotlinx.coroutines.delay

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hints: List<String> = listOf(
        "Events",
        "Tech talks",
        "Venues",
        "Workshops",
        "Meetups"
    ),
    onClick: () -> Unit
) {
    var currentIndex by remember { mutableStateOf(0) }

    //auto change
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentIndex = (currentIndex + 1) % hints.size
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(backgroundSecondary)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "Search",
            tint = iconPrimary
        )
        Spacer(modifier = Modifier.width(10.dp))

        // Static "Search"
        Text(
            text = "Search for",
            color = light_white,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.width(4.dp))

        //vertical animation
        AnimatedContent(
            targetState = hints[currentIndex],
            transitionSpec = {
//                scaleIn() + fadeIn() togetherWith scaleOut() + fadeOut()
                slideInVertically { it } + fadeIn() togetherWith slideOutVertically { -it } + fadeOut()
            },
            label = "SearchHintAnimation"
        ) { targetHint ->
            Text(
                text = targetHint,
                color = light_white,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}