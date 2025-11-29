package com.example.together.feature.auth.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.R
import com.example.together.ui.theme.backgroundPrimary
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.primary

@Composable
fun GetStartedScreen(
    paddingValues: PaddingValues,
    onSignUpWithEmail: () -> Unit,
    onLoginClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        //Smooth bottom glow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(900.dp)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,                     // fully blend with screen
                            primary.copy(alpha = 0.45f),           // strongest glow in middle
                            Color.Transparent                      // fade out at very bottom
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Together",
                color = primary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 12.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Let get started",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )


            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Register for events and manage the activities you plan to attend with Together.",
                color = light_white,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onSignUpWithEmail,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(999.dp),
                colors = ButtonDefaults.buttonColors(primary)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "Next",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Continue With Email")
            }

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 13.sp,
                    color = Color.White
                )

                Text(
                    text = "Log in",
                    fontSize = 13.sp,
                    color = primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable{
                        onLoginClick()
                    }
                )
            }


            Spacer(modifier = Modifier.weight(1f))

            Text(
                "By signing up or logging in, I accept the Together",
                color = light_white,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Terms of Service")
                    }
                    append(" and ")
                    withStyle(
                        SpanStyle(
                            color = primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Privacy Policy")
                    }
                },
                fontSize = 13.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
