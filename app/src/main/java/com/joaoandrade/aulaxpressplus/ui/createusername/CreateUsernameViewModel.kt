package com.joaoandrade.aulaxpressplus.ui.createusername

import androidx.lifecycle.ViewModel
import com.joaoandrade.aulaxpressplus.shared.bases.BaseViewModel
import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class CreateUsernameViewModel
    @Inject
    constructor(
        override val navigationProvider: NavigationProvider,
    ) : ViewModel(), BaseViewModel<CreateUsernameUiState>, CreateUsernameCommandReceiver {
        private val _uiState = MutableStateFlow(CreateUsernameUiState())
        override val uiState = _uiState.asStateFlow()

        override fun createUsername() {
    	}
    }
