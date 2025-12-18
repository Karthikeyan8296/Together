package com.example.together.feature.dashboard.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.together.R
import com.example.together.feature.common.components.HomeCategoryTab
import com.example.together.feature.common.components.HomeCategoryTabRow
import com.example.together.feature.common.components.InputField
import com.example.together.ui.theme.iconPrimary

@Composable
fun SearchScreen(
    paddingValues: PaddingValues
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var query by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        //small delay and then focus
        focusRequester.requestFocus()
    }

    val tabs = listOf(
        HomeCategoryTab("tech", "Tech", R.drawable.code),
        HomeCategoryTab("design", "Design", R.drawable.design),
        HomeCategoryTab("startup", "Startup", R.drawable.start_up),
        HomeCategoryTab("business", "Business", R.drawable.business),
    )
    var selectedTabId by remember { mutableStateOf(tabs.first().id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { focusManager.clearFocus() }
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        InputField(
            value = query,
            onValueChange = { query = it },
            placeholder = "Search events, speakers, venues…",
            modifier = Modifier.focusRequester(focusRequester),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = "back",
                    modifier = Modifier.size(18.dp),
                    tint = iconPrimary
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        HomeCategoryTabRow(
            tabs = tabs,
            selectedId = selectedTabId,
            onTabSelected = { tab ->
                selectedTabId = tab.id
                // TODO: filter your event list based on tab.id
            }
        )

    }
}