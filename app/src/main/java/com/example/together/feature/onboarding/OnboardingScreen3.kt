package com.example.together.feature.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.together.core.navigation.Routes
import com.example.together.core.navigation.onBoarding.OnBoardingRoutes
import com.example.together.feature.common.components.Header
import com.example.together.feature.common.components.InputField
import com.example.together.feature.common.components.PrimaryButton
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun OnboardingScreen3(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModal: OnboardingViewModel
) {
    var company by rememberSaveable { mutableStateOf(viewModal.companyName) }
    var designation by rememberSaveable { mutableStateOf(viewModal.designation) }
    var yearsOfExp by rememberSaveable { mutableStateOf(viewModal.experience.toFloat()) }
    // label above slider (Fresher / X years)
    val yearsText = when (yearsOfExp.toInt()) {
        0 -> "Fresher"
        1 -> "1 year"
        20 -> "20+ years"
        else -> "${yearsOfExp.toInt()} years"
    }
    val focusManager = LocalFocusManager.current
    val isAllFieldFilled = company.isNotBlank() && designation.isNotBlank() && yearsOfExp >= 0f

    val isLoading = viewModal.isLoading
    val isSuccess = viewModal.isSuccess
    val error = viewModal.error

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            navController.navigate(Routes.APP_GRAPH) {
                popUpTo(OnBoardingRoutes.ONBOARDING_1) { inclusive = true }
            }
        }
    }

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
            }
    )
    {
        Header(
            title = "Your Work Experience",
            subTitle = "Highlight your role so the right opportunities find you at the event"
        )

        Text(text = "Company name", fontSize = 14.sp, color = white, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))

        InputField(
            value = company,
            onValueChange = { company = it },
            placeholder = "eg., Infosys, Google",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(text = "Designation", fontSize = 14.sp, color = white, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))

        InputField(
            value = designation,
            onValueChange = { designation = it },
            placeholder = "eg., Software Engineer, Product Manager",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Years of Experience",
            fontSize = 14.sp,
            color = white,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = yearsText,
            fontSize = 13.sp,
            color = light_white,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))

        Slider(
            value = yearsOfExp,
            onValueChange = { yearsOfExp = it },
            valueRange = 0f..20f,
            steps = 19, // discrete 0–20
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = primary,
                activeTrackColor = primary,
                inactiveTrackColor = light_white.copy(alpha = 0.3f)
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "0",
                fontSize = 12.sp,
                color = light_white
            )
            Text(
                text = "20+",
                fontSize = 12.sp,
                color = light_white
            )
        }

        if (error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(error, color = Color.Red, fontSize = 12.sp)
        }

        PrimaryButton(
            text = "Continue",
            enabled = isAllFieldFilled && !isLoading,
            isLoading = isLoading,
            onClick = {
                viewModal.saveOnboarding3(
                    company, designation, yearsOfExp.toInt()
                )
                viewModal.submitOnboarding()
            },
            modifier = Modifier.padding(top = 20.dp)
        )

        PrimaryButton(
            text = "Logout",
            onClick = viewModal::logout
        )
//        CircularWavyProgressIndicator()
    }
}