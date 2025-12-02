package com.example.together.feature.auth.login

import androidx.lifecycle.ViewModel
import com.example.together.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false
)

@HiltViewModel
class LoginViewModal @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String){
        _uiState.value = _uiState.value.copy(email = value, error = null)
    }
    fun onPasswordChange(value: String){
        _uiState.value = _uiState.value.copy(password = value, error = null)
    }

    fun handleLogin() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        if(email.isBlank() || password.isBlank() || _uiState.value.isLoading) return
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            authRepository.login(email, password)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoggedIn = true
                    )
                }.onFailure {e->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoggedIn = false,
                        error = e.message ?: "LoginFailed"
                    )
                }
        }
    }
}