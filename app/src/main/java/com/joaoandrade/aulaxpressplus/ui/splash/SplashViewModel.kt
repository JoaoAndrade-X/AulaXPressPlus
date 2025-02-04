package com.joaoandrade.aulaxpressplus.ui.splash

import androidx.lifecycle.ViewModel
import com.joaoandrade.aulaxpressplus.shared.bases.BaseViewModel
import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        override val navigationProvider: NavigationProvider
    ) : ViewModel(), BaseViewModel<SplashUiState>, SplashCommandReceiver {
        private val _uiState = MutableStateFlow(SplashUiState())
        override val uiState = _uiState.asStateFlow()
    }