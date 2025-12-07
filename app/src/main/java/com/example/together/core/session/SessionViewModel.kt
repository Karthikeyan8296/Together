package com.example.together.core.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.together.core.navigation.Routes
import com.example.together.data.local.dataStore.AuthDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val authDataStore: AuthDataStore
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Routes>(Routes.LOADING)
    val startDestination: StateFlow<Routes?> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            authDataStore.authTokens.collect { tokens ->
                _startDestination.value = Routes.LOADING
                delay(2500)

                _startDestination.value = when {
                    tokens.accessToken.isNullOrBlank() -> {
                        Routes.AUTH_GRAPH// Not logged in
                    }

                    tokens.onBoardingStatus -> {
                        Routes.APP_GRAPH//Logged in + onboarded
                    }

                    else -> {
                        Routes.ONBOARDING_GRAPH//Logged in but NOT onboarded
                    }
                }
            }
        }
    }
}