package com.example.together.feature.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.together.feature.common.components.PrimaryButton
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onLogout: () -> Unit,
    viewModal: HomeScreenViewModel = hiltViewModel()
) {

    val state by viewModal.uiState.collectAsStateWithLifecycle()
    val isLoggedOut by viewModal.isLoggedOut.collectAsStateWithLifecycle()

    LaunchedEffect(isLoggedOut) {
        if (isLoggedOut) {
            onLogout()
            viewModal.consumeLogout()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Home Screen",
            fontSize = 24.sp,
            color = white,
            fontWeight = FontWeight.SemiBold
        )
        // Optional: show error text
        state.error?.let {
            Text(
                text = it,
                color = primary,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Spacer(modifier.height(12.dp))

        Text(
            text = "Your Id: ${state.id}",
            fontSize = 14.sp,
            color = white,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier.height(4.dp))

        Text(
            text = "Email: ${state.email}",
            fontSize = 14.sp,
            color = white,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier.height(8.dp))

        PrimaryButton(
            onClick = { viewModal.logout() },
            text = "Logout",
            isLoading = state.isLoading
        )
    }
}