package com.example.together.feature.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.together.ui.theme.backgroundSecondary
import com.example.together.ui.theme.error
import com.example.together.ui.theme.light_white
import com.example.together.ui.theme.primary
import com.example.together.ui.theme.white
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

// --- Models ---

data class AddressSuggestion(
    val properties: AddressProperties,
    val geometry: AddressGeometry
)

data class AddressProperties(
    val formatted: String?,
    val address_line1: String?,
    val address_line2: String?,
    val city: String?,
    val state: String?,
    val postcode: String?,
    val country: String?,
    val lat: Double?,
    val lon: Double?
)

data class AddressGeometry(
    val coordinates: List<Double> // [lon, lat]
)

data class GeoapifyResponse(
    val features: List<AddressSuggestion>?
)

private const val GEOAPIFY_API_KEY =
    "3e7767d18bd547e9bc4e414bdbcfa721" // TODO: move to secure config

private val geoClient = OkHttpClient()
private val gson = Gson()


@Composable
fun LocationPicker(
    selected: String?,
    onSelectedChange: (String?) -> Unit,
    placeholder: String = "Start typing provinces, cities",
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf(TextFieldValue(selected.orEmpty())) }
    var suggestions by remember { mutableStateOf<List<AddressSuggestion>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(query.text) {
        val text = query.text
        if (text.length < 2) {
            suggestions = emptyList()
            loading = false
            return@LaunchedEffect
        }

        loading = true
        // small debounce so we don't spam API
        delay(300)

        val result = fetchGeoapifySuggestions(text)
        suggestions = result
        loading = false
    }

    // Keep query synced if parent changes selected externally
    LaunchedEffect(selected) {
        if (selected != null && selected != query.text) {
            query = TextFieldValue(selected)
        }
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                onSelectedChange(null) // reset selection when user types
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(backgroundSecondary, RoundedCornerShape(14.dp)),
            singleLine = true,
            textStyle = TextStyle(
                color = white,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    color = light_white,
                    fontSize = 15.sp
                )
            },
            trailingIcon = {
                if (query.text.isNotEmpty()) {
                    Text(
                        text = "✕",
                        color = light_white,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                query = TextFieldValue("")
                                onSelectedChange(null)
                                suggestions = emptyList()
                            }
                    )
                }
            },
            isError = false,
            shape = RoundedCornerShape(14.dp),
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

        // ⬇️ Dropdown (matches your dark theme container)
        if (query.text.isNotEmpty() && selected == null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .background(backgroundSecondary, RoundedCornerShape(14.dp))
            ) {
                when {
                    loading -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(
                                strokeWidth = 2.dp,
                                color = primary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    suggestions.isNotEmpty() -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 190.dp)
                        ) {
                            items(suggestions) { item ->
                                val label = item.properties.formatted
                                    ?: buildString {
                                        append(item.properties.city ?: item.properties.state ?: "")
                                        if (!item.properties.country.isNullOrBlank()) {
                                            if (isNotEmpty()) append(", ")
                                            append(item.properties.country)
                                        }
                                    }

                                Text(
                                    text = label,
                                    color = white,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null
                                        ) {
                                            onSelectedChange(label)
                                            query = TextFieldValue(label)
                                            suggestions = emptyList()
                                        }
                                        .padding(horizontal = 16.dp, vertical = 12.dp)
                                )
                            }
                        }
                    }

                    else -> {
                        // No suggestions - you can add a "No results" text if you want
                    }
                }
            }
        }
    }
}


private suspend fun fetchGeoapifySuggestions(
    query: String
): List<AddressSuggestion> = withContext(Dispatchers.IO) {
    try {
        val url =
            "https://api.geoapify.com/v1/geocode/autocomplete?text=${
                java.net.URLEncoder.encode(query, "UTF-8")
            }&filter=countrycode:in&limit=5&apiKey=$GEOAPIFY_API_KEY"

        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response = geoClient.newCall(request).execute()
        val body = response.body?.string() ?: return@withContext emptyList<AddressSuggestion>()

        val parsed = gson.fromJson(body, GeoapifyResponse::class.java)
        val rawFeatures = parsed.features ?: emptyList()

        // Apply same filtering as RN: only city + country
        rawFeatures.mapNotNull { item ->
            val city = item.properties.city ?: item.properties.state
            val country = item.properties.country
            if (city != null && country != null) {
                item.copy(
                    properties = item.properties.copy(
                        formatted = "$city, $country"
                    )
                )
            } else {
                null
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}
