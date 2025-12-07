package com.example.together.data.repository

import com.example.together.data.remote.api.AuthAPI
import com.example.together.data.remote.dto.OnboardingRequest
import com.example.together.data.remote.dto.OnboardingResponse
import com.example.together.domain.repository.OnBoardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val API: AuthAPI
) : OnBoardingRepository {
    override suspend fun createOnboarding(body: OnboardingRequest): Result<OnboardingResponse> =
        runCatching {
            API.createOnBoarding(body)
        }
}