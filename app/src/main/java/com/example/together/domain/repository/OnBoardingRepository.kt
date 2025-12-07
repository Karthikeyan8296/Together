package com.example.together.domain.repository

import com.example.together.data.remote.dto.OnboardingRequest
import com.example.together.data.remote.dto.OnboardingResponse

interface OnBoardingRepository {
    suspend fun createOnboarding(body: OnboardingRequest): Result<OnboardingResponse>
}