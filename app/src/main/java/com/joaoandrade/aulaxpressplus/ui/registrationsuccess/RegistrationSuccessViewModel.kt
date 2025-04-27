package com.joaoandrade.aulaxpressplus.ui.registrationsuccess

import androidx.lifecycle.ViewModel
import com.joaoandrade.aulaxpressplus.shared.bases.BaseViewModel
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class RegistrationSuccessViewModel
    @Inject
    constructor(
        override val navigationProvider: NavigationProvider,
    ) : ViewModel(), BaseViewModel<RegistrationSuccessUiState>, RegistrationSuccessCommandReceiver {
        private val _uiState = MutableStateFlow(RegistrationSuccessUiState())
        override val uiState = _uiState.asStateFlow()

        override fun navigateToLogin() {
            navigationProvider.navigate(Destination.LOGIN_DESTINATION)
    	}
    }
