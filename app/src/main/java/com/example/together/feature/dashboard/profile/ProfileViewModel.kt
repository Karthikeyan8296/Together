package com.example.together.feature.dashboard.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.together.data.local.dataStore.AuthDataStore
import com.example.together.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileState(
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authDataStore: AuthDataStore
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState> = _uiState.asStateFlow()

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut: StateFlow<Boolean> = _isLoggedOut.asStateFlow();

    fun logout() {
        viewModelScope.launch {
            val token = authDataStore.authTokens.first()
            val refreshToken = token.refreshToken

            if (refreshToken.isNullOrBlank()) {
                authDataStore.clearTokens()
                _isLoggedOut.value = true
                return@launch
            }

            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            authRepository.logout(refreshToken)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    _isLoggedOut.value = true
                }
                .onFailure { e ->
                    _uiState.value =
                        _uiState.value.copy(isLoading = true, error = e.message ?: "Logout Failed")
                    _isLoggedOut.value = false
                }
        }
    }

    fun consumeLogout() {
        if (_isLoggedOut.value) {
            _isLoggedOut.value = false
        }
    }
}