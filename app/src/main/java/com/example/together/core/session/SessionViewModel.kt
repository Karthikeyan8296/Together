package com.example.together.core.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.together.core.navigation.Routes
import com.example.together.data.local.dataStore.AuthDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val authDataStore: AuthDataStore
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Routes?>(null)
    val startDestination: StateFlow<Routes?> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            authDataStore.authTokens.collect { tokens ->
                _startDestination.value = if (tokens.isLoggedIn) {
                    Routes.ONBOARDING_GRAPH
                } else {
                    Routes.AUTH_GRAPH
                }
            }
        }
    }
}