package com.example.together.feature.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.together.feature.common.components.InputField
import com.example.together.feature.common.components.PrimaryButton
import com.example.together.ui.theme.white

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onLoginComplete: (Boolean) -> Unit,
    viewModal: LoginViewModal = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current

    val state by viewModal.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 32.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            },
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Together",
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp,
                color = white,
                letterSpacing = (-1).sp,
                modifier = Modifier.padding(bottom = 32.dp),
            )
        }

        Text(text = "Email", fontSize = 16.sp, color = white)
        Spacer(modifier = Modifier.height(4.dp))

        InputField(
            value = state.email,
            onValueChange = viewModal::onEmailChange,
            placeholder = "example@email.com",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(text = "Password", fontSize = 16.sp, color = white)
        Spacer(modifier = Modifier.height(4.dp))

        InputField(
            value = state.password,
            onValueChange = viewModal::onPasswordChange,
            placeholder = "Password",
            isPassword = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        PrimaryButton(
            text = "Login",
            onClick = {
                focusManager.clearFocus()
                viewModal.handleLogin { isOnboarded ->
                    onLoginComplete(isOnboarded)
                }
            },
            enabled = state.email.isNotBlank() && state.password.isNotBlank() && !state.isLoading,
            modifier = Modifier.padding(top = 20.dp),
            isLoading = state.isLoading,
        )
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.weight(1f))
    }
}