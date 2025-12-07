package com.example.together.feature.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.ui.Alignment
import com.example.together.ui.theme.backgroundSecondary
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white
import com.example.together.ui.theme.light_white
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelectChips(
    options: List<String>,
    selected: Set<String>,
    onSelectionChange: (Set<String>) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    FlowRow(
        modifier = modifier.padding(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { label ->
            val isSelected = selected.contains(label)

            val bg = if (isSelected) primary.copy(alpha = 0.12f) else Color.Transparent
            val borderColor = if (isSelected) primary else backgroundSecondary
            val textColor = if (isSelected) primary else white

            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(36.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(bg)
                    .border(
                        width = 1.5.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(999.dp)
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        val newSelection = selected.toMutableSet()
                        if (isSelected) {
                            newSelection.remove(label)
                        } else {
                            newSelection.add(label)
                        }
                        onSelectionChange(newSelection)
                    }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    color = textColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
