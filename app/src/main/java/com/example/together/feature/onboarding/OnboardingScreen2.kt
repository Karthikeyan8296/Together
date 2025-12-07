package com.example.together.feature.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.feature.common.components.Header
import com.example.together.feature.common.components.InputField
import com.example.together.feature.common.components.MultiSelectChips
import com.example.together.feature.common.components.PrimaryButton
import com.example.together.ui.theme.white

@Composable
fun OnboardingScreen2(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    handleContinue: () -> Unit,
    viewModal: OnboardingViewModel
) {

    var link by remember { mutableStateOf("") }
    var selectedExpertise by remember { mutableStateOf(setOf<String>()) }

    val focusManager = LocalFocusManager.current

    val isAllFieldFilled = link.isNotBlank() && selectedExpertise.isNotEmpty()

    val expertiseOptions = listOf(
        "📱 Mobile App Dev",
        "🌐 Web Dev",
        "🎨 UI/UX",
        "🎮 Game Dev",
        "📊 Data Sci",
        "🤖 AI/ML",
        "🔐 CyberSec",
        "☁️ Cloud",
        "📡 IoT",
        "⛓️ Blockchain",
        "🕶️ AR/VR",
        "🧊 3D Art",
        "🧪 Testing",
        "⚙️ DevOps",
        "🔌 Embedded",
        "🗄️ DB Admin",
        "📈 SEO",
        "🧑‍💼 PM",
        "🎬 Video Edit",
        "✍️ Content Write",
        "📣 Marketing"
    )

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
            title = "Your Professional Profile",
            subTitle = "Help us connect you with the right events and opportunities"
        )

        Text(text = "Full Name", fontSize = 14.sp, color = white, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))

        InputField(
            value = link,
            onValueChange = { link = it },
            placeholder = "https://linkedin.com/in/yourprofile",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Area of Expertise",
            fontSize = 14.sp,
            color = white,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))

        MultiSelectChips(
            options = expertiseOptions,
            selected = selectedExpertise,
            onSelectionChange = { selectedExpertise = it }
        )

        PrimaryButton(
            text = "Continue",
            onClick = {
                viewModal.saveOnboarding2(
                    link, selectedExpertise
                )
                handleContinue()
            },
            enabled = isAllFieldFilled,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}