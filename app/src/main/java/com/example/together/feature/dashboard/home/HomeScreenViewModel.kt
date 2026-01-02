package com.example.together.feature.dashboard.home

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

data class HomeUiState(
    val email: String? = null,
    val id: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authDataStore: AuthDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    //we are using init here because when the screen is opened, this will be launched
    init {
        viewModelScope.launch {
            authDataStore.authTokens.collect { tokens ->
                _uiState.value = HomeUiState(
                    email = tokens.email,
                    id = tokens.id
                )
            }
        }
    }


}