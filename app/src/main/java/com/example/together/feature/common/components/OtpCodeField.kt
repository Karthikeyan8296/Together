package com.example.together.feature.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.ui.theme.backgroundSecondary
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white

@Composable
fun OtpCodeField(
    code: String,
    onCodeChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    digits: Int = 6
) {
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = code,
        onValueChange = { new ->
            // only digits, limit length
            val filtered = new.filter { it.isDigit() }.take(digits)
            onCodeChange(filtered)
        },
        modifier = modifier.onFocusChanged { isFocused = it.isFocused },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(color = Color.Transparent), // we don’t use the default text
        decorationBox = { innerTextField ->
            Box {
                Row {
                    repeat(digits) { index ->
                        val char = code.getOrNull(index)?.toString() ?: ""
                        val isActiveSlot =
                            isFocused && (index == code.length || (index == digits - 1 && code.length == digits))

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    color = backgroundSecondary,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (isActiveSlot) primary else Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (char.isEmpty()) "-" else char,
                                color = white,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        if (index != digits - 1) {
                            Box(modifier = Modifier.size(8.dp)) // gap
                        }
                    }
                }

                // hidden real text field to capture input
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .alpha(0f) // invisible but focusable
                ) {
                    innerTextField()
                }
            }
        }
    )
}
