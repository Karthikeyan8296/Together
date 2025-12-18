package com.example.together.feature.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.R
import com.example.together.ui.theme.iconPrimary
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.white

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    name: String,
    location: String,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.location_icon),
                tint = light_white,
                modifier = Modifier.size(20.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column() {
                Text(
                    text = name,
                    color = white,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 10.sp
                )
                Text(text = location, color = white, fontSize = 13.sp)
            }
        }

        Icon(
            painter = painterResource(R.drawable.user_icon),
            contentDescription = null,
            tint = iconPrimary,
            modifier = Modifier.clickable { onProfileClick() }
        )
    }
}