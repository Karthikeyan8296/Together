package com.example.together.feature.onboarding

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.together.data.local.dataStore.AuthDataStore
import com.example.together.data.remote.dto.OnboardingRequest
import com.example.together.domain.repository.AuthRepository
import com.example.together.domain.repository.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository,
    private val authDataStore: AuthDataStore,
    private val authRepository: AuthRepository,
) : ViewModel() {

    var fullName by mutableStateOf("")
        private set
    var phoneNumber by mutableStateOf("")
        private set
    var location by mutableStateOf("")
        private set

    var linkedin by mutableStateOf("")
        private set
    var expertise by mutableStateOf(setOf<String>())
        private set

    var companyName by mutableStateOf("")
        private set
    var designation by mutableStateOf("")
        private set
    var experience by mutableStateOf(0)
        private set

    //API state
    var isLoading by mutableStateOf(false)
        private set
    var isSuccess by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    //Setter from other screens
    fun saveOnboarding1(name: String, phone: String, loc: String) {
        fullName = name
        phoneNumber = phone
        location = loc
    }

    fun saveOnboarding2(link: String, exp: Set<String>) {
        linkedin = link
        expertise = exp
    }

    fun saveOnboarding3(comp: String, desig: String, expYears: Int) {
        companyName = comp
        designation = desig
        experience = expYears
    }

    fun submitOnboarding() {
        viewModelScope.launch {
            isLoading = true
            error = null

            val body = OnboardingRequest(
                fullName = fullName.trim(),
                phoneNumber = phoneNumber.trim(),
                location = location.trim(),
                linkedin = linkedin.trim(),
                expertise = expertise.joinToString(","),
                CompanyName = companyName.trim(),
                Designation = designation.trim(),
                yearOfExperience = experience
            )

            Log.d("ONBOARDING_BODY", body.toString())

            onBoardingRepository.createOnboarding(body = body)
                .onSuccess {
                    authDataStore.updateOnboardingStatus(status = true)
                    isSuccess = true
                }
                .onFailure {
                    error = it.message
                }
            isLoading = false
        }
    }

    fun logout() {
        viewModelScope.launch {
            val token = authDataStore.authTokens.first()
            val refreshToken = token.refreshToken

            if (refreshToken.isNullOrBlank()) {
                authDataStore.clearTokens()
                return@launch
            }

            isLoading = true
            authRepository.logout(refreshToken)
                .onSuccess {
                    isLoading = false
                }
                .onFailure {
                    isLoading = false
                    error = it.message
                }
        }
    }
}