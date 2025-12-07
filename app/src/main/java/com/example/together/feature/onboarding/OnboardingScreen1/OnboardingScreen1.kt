package com.example.together.feature.onboarding.OnboardingScreen1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.feature.common.components.Header
import com.example.together.feature.common.components.InputField
import com.example.together.feature.common.components.LocationPicker
import com.example.together.feature.common.components.PrimaryButton
import com.example.together.ui.theme.white

@Composable
fun OnboardingScreen1(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    handleContinue: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf<String?>(null) }
    val isAllFieldFilled =
        name.isNotBlank() && phoneNumber.isNotBlank() && location != null && phoneNumber.length == 10

    val focusManager = LocalFocusManager.current

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
        Header(
            title = "Tell Us About You",
            subTitle = "Share your basic details so we can personalize your experience"
        )

        Text(text = "Full Name", fontSize = 14.sp, color = white, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))

        InputField(
            value = name,
            onValueChange = { name = it },
            placeholder = "Enter your full name",
            modifier = Modifier.padding(bottom = 16.dp)
        )


        Text(text = "Phone Number", fontSize = 14.sp, color = white, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))

        InputField(
            value = phoneNumber,
            onValueChange = { input ->
                val digitOnly = input.filter { it.isDigit() }
                if (digitOnly.length <= 10) {
                    phoneNumber = digitOnly
                }
            },
            placeholder = "+91 XXXXX-XXXXX",
            modifier = Modifier.padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )


        Text(text = "Location", fontSize = 14.sp, color = white, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))

        LocationPicker(
            selected = location,
            onSelectedChange = { location = it },
            placeholder = "Start typing your province cities",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        PrimaryButton(
            text = "Continue",
            onClick = handleContinue,
            enabled = isAllFieldFilled,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}