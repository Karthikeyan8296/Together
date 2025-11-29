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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.feature.common.components.Header
import com.example.together.feature.common.components.InputField
import com.example.together.feature.common.components.OtpCodeField
import com.example.together.feature.common.components.PrimaryButton
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private enum class SignUpStep {
    EMAIL,
    CODE,
    PASSWORD,
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    OnSignInComplete: () -> Unit
) {

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var currentStep by remember { mutableStateOf(SignUpStep.EMAIL) }
    var isLoading by remember { mutableStateOf(false) }

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
        Header()
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

        when (currentStep) {
            SignUpStep.EMAIL -> {
                Text(text = "Email", fontSize = 16.sp, color = white)
                Spacer(modifier = Modifier.height(4.dp))
                InputField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "example@email.com",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )
                PrimaryButton(
                    text = "Continue",
                    onClick = {
                        if (email.isNotBlank() && !isLoading) {
                            focusManager.clearFocus()
                            isLoading = true

                            scope.launch {
                                delay(3000)
                                isLoading = false
                                currentStep = SignUpStep.CODE
                            }
                        }
                    },
                    enabled = email.isNotBlank(),
                    modifier = Modifier.padding(top = 20.dp),
                    isLoading = isLoading
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
                    code = code,
                    digits = 6,
                    onCodeChange = { code = it }
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
                            // later: call backend resend code with email
                        }
                    )
                }

                PrimaryButton(
                    text = "Verify",
                    onClick = {
                        if (code.length > 5 && !isLoading) {
                            focusManager.clearFocus()
                            isLoading = true

                            scope.launch {
                                delay(3000)
                                isLoading = false
                                currentStep = SignUpStep.PASSWORD
                            }
                        }
                    },
                    enabled = code.length > 5,
                    modifier = Modifier.padding(top = 20.dp),
                    isLoading = isLoading,
                )
            }

            SignUpStep.PASSWORD -> {
                Text(text = "Set Password", fontSize = 16.sp, color = white)
                Spacer(modifier = Modifier.height(4.dp))
                InputField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "password",
                    isPassword = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                )
                PrimaryButton(
                    text = "Create Account",
                    onClick = {
                        if (password.isNotBlank() && !isLoading) {
                            focusManager.clearFocus()
                            isLoading = true

                            scope.launch {
                                delay(3000)
                                isLoading = false
                                OnSignInComplete()
                            }
                        }
                    },
                    enabled = password.isNotBlank(),
                    modifier = Modifier.padding(top = 20.dp),
                    isLoading = isLoading
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