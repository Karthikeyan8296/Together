package com.example.together.feature.auth.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.together.feature.common.components.InputField
import com.example.together.feature.common.components.OtpCodeField
import com.example.together.feature.common.components.PrimaryButton
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onSignInComplete: () -> Unit,
    viewModal: SignUpViewModal = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current

    //getting the state from view modal
    val state by viewModal.uiState.collectAsStateWithLifecycle()

    //navigate once signup finished
    LaunchedEffect(state.isSignedUp) {
        if (state.isSignedUp) {
            onSignInComplete()
            viewModal.consumeSignedUpFlag()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
            .padding(horizontal = 32.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Create Your Account",
            fontSize = 24.sp,
            color = white,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Join for free and stay connected with events and experiences near you.",
            fontSize = 14.sp,
            color = light_white,
            lineHeight = 18.sp,
        )

        Spacer(modifier = Modifier.height(24.dp))


        // Optional: show error text
        state.error?.let {
            Text(
                text = it,
                color = primary,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }


        when (state.currentStep) {
            SignUpStep.EMAIL -> {
                Text(text = "Email", fontSize = 16.sp, color = white)
                Spacer(modifier = Modifier.height(4.dp))
                InputField(
                    value = state.email,
                    onValueChange = viewModal::onEmailChange,
                    placeholder = "example@email.com",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )
                PrimaryButton(
                    text = "Continue",
                    onClick = {
                        focusManager.clearFocus()
                        viewModal.submitEmail()
                    },
                    enabled = state.email.isNotBlank() && !state.isLoading,
                    modifier = Modifier.padding(top = 20.dp),
                    isLoading = state.isLoading
                )
            }

            SignUpStep.CODE -> {
                Text(
                    text = "Sign up code",
                    fontSize = 16.sp,
                    color = white,
                )
                Spacer(modifier = Modifier.height(8.dp))
                OtpCodeField(
                    code = state.code,
                    digits = 6,
                    onCodeChange = viewModal::onCodeChange
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "You didn't receive any code? ",
                        fontSize = 14.sp,
                        color = light_white
                    )
                    Text(
                        text = "Resend Code",
                        fontSize = 14.sp,
                        color = primary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            // same email, just re-call the api
                            focusManager.clearFocus()
                            viewModal.submitEmail()
                        }
                    )
                }

                PrimaryButton(
                    text = "Verify",
                    onClick = {
                        focusManager.clearFocus()
                        viewModal.verifyCode()
                    },
                    enabled = state.code.length >= 6 && !state.isLoading,
                    modifier = Modifier.padding(top = 20.dp),
                    isLoading = state.isLoading
                )
            }

            SignUpStep.PASSWORD -> {
                Text(text = "Set Password", fontSize = 16.sp, color = white)
                Spacer(modifier = Modifier.height(4.dp))
                InputField(
                    value = state.password,
                    onValueChange = viewModal::onPasswordChange,
                    placeholder = "password",
                    isPassword = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                )
                PrimaryButton(
                    text = "Create Account",
                    onClick = {
                        focusManager.clearFocus()
                        viewModal.completeSignUp()
                    },
                    enabled = state.password.isNotBlank() && !state.isLoading,
                    modifier = Modifier.padding(top = 20.dp),
                    isLoading = state.isLoading
                )
            }
        }
    }
}


@Preview(
    backgroundColor = 0xFF0D051A,
    showBackground = true
)
@Composable
fun SignupPreview(modifier: Modifier = Modifier) {
}