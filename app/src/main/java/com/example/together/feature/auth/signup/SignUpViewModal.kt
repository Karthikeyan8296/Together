package com.example.together.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.together.data.local.dataStore.AuthDataStore
import com.example.together.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class SignUpStep {
    EMAIL,
    CODE,
    PASSWORD
}

data class SignUpUiState(
    val email: String = "",
    val code: String = "",
    val password: String = "",
    val currentStep: SignUpStep = SignUpStep.EMAIL,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSignedUp: Boolean = false
)


@HiltViewModel
class SignUpViewModal @Inject constructor(
    private val authRepository: AuthRepository,
    private val authDataStore: AuthDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    //temp storing
    private var signupToken: String? = null;

    fun onEmailChange(value: String) {
        _uiState.value = _uiState.value.copy(email = value, error = null)
    }

    fun onCodeChange(value: String) {
        _uiState.value = _uiState.value.copy(code = value, error = null)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value, error = null)
    }


    fun submitEmail() {
        val email = _uiState.value.email
        if (email.isBlank() || _uiState.value.isLoading) return

        //loading set to true now,
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            authRepository.sendOtp(email)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        currentStep = SignUpStep.CODE
                    )
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to send Code"
                    )
                }
        }
    }


    fun verifyCode() {
        val email = _uiState.value.email
        val code = _uiState.value.code

        if (code.length < 6 || _uiState.value.isLoading) return

        //we use copy to change the state here!
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            authRepository.verifyEmail(email, code)
                //that will return signupToken so string!
                .onSuccess { token ->
                    signupToken = token
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        currentStep = SignUpStep.PASSWORD
                    )
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "Invalid code"
                    )
                }
        }
    }


    fun completeSignUp() {
        val password = _uiState.value.password
        val token = signupToken ?: return

        if (password.isBlank() || _uiState.value.isLoading) return
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            authRepository.signUp(token, password)
                .onSuccess { res ->
                    authDataStore.saveTokens(
                        accessToken = res.accessToken,
                        refreshToken = res.accessToken,
                        email = res.user.email,
                        id = res.user.id
                    )

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSignedUp = true
                    )
                }.onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSignedUp = false,
                        error = e.message ?: "Signup Failed",
                    )
                }
        }
    }

    fun consumeSignedUpFlag() {
        if (_uiState.value.isSignedUp) {
            _uiState.value = _uiState.value.copy(isSignedUp = false)
        }
    }

}