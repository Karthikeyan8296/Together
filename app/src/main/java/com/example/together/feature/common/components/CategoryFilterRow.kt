package com.example.together.feature.common.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.ui.theme.backgroundSecondary
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.primary

data class HomeCategoryTab(
    val id: String,
    val label: String,
    val iconRes: Int
)

@Composable
fun HomeCategoryTabRow(
    modifier: Modifier = Modifier,
    tabs: List<HomeCategoryTab>,
    selectedId: String,
    onTabSelected: (HomeCategoryTab) -> Unit
) {
    if (tabs.isEmpty()) return

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 12.dp, bottom = 4.dp)
    ) {
        val tabCount = tabs.size
        val tabWidth = maxWidth / tabCount

        val selectedIndex = tabs.indexOfFirst { it.id == selectedId }
            .coerceAtLeast(0)

        // animate horizontal offset of the indicator
        val indicatorOffsetX by animateDpAsState(
            targetValue = tabWidth * selectedIndex,
            animationSpec = spring(stiffness = 500f),
            label = "tabIndicatorOffset"
        )

        // --- Tabs row ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, tab ->
                val isSelected = index == selectedIndex

                val iconTint by animateColorAsState(
                    targetValue = if (isSelected) primary else light_white,
                    label = "iconTintAnim"
                )
                val textColor by animateColorAsState(
                    targetValue = if (isSelected) primary else light_white,
                    label = "textColorAnim"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onTabSelected(tab) }
                ) {
                    Icon(
                        painter = painterResource(tab.iconRes),
                        contentDescription = tab.label,
                        tint = iconTint,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = tab.label.uppercase(),
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        // baseline
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(1.dp)
                .background(backgroundSecondary)
        )

        // animated sliding indicator
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = indicatorOffsetX)
                .width(tabWidth)
                .height(3.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(primary)
        )
    }
}
