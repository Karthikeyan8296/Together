package com.example.together.feature.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.ui.theme.backgroundSecondary
import com.example.together.ui.theme.error
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    errorText: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation: VisualTransformation =
        if (isPassword && !passwordVisible) PasswordVisualTransformation()
        else VisualTransformation.None

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(backgroundSecondary, RoundedCornerShape(14.dp)),
        singleLine = singleLine,
        textStyle = TextStyle(
            color = white,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        ),
        placeholder = placeholder?.let {
            { Text(text = it, color = light_white, fontSize = 15.sp) }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        isError = errorText != null,
        shape = RoundedCornerShape(14.dp),
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        tint = light_white
                    )
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = backgroundSecondary,
            unfocusedContainerColor = backgroundSecondary,
            errorContainerColor = backgroundSecondary,

            focusedIndicatorColor = primary,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = error,

            focusedLabelColor = primary,
            unfocusedLabelColor = light_white,
            errorLabelColor = error,

            cursorColor = primary,
            focusedTextColor = white,
            unfocusedTextColor = white,
            errorTextColor = white,

            focusedPlaceholderColor = light_white,
            unfocusedPlaceholderColor = light_white,
            errorPlaceholderColor = light_white
        )

    )

    if (errorText != null) {
        Text(
            text = errorText,
            color = error,
            fontSize = 11.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
