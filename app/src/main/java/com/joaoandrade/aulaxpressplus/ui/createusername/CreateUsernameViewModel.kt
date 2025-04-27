package com.joaoandrade.aulaxpressplus.ui.createusername

import androidx.lifecycle.ViewModel
import com.joaoandrade.aulaxpressplus.navigation.params.CreateUsernameParam
import com.joaoandrade.aulaxpressplus.shared.bases.BaseViewModel
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class CreateUsernameViewModel
    @Inject
    constructor(
        override val navigationProvider: NavigationProvider,
    ) : ViewModel(), BaseViewModel<CreateUsernameUiState>, CreateUsernameCommandReceiver {
        private val _uiState = MutableStateFlow(CreateUsernameUiState())
        override val uiState = _uiState.asStateFlow()

        private val param: CreateUsernameParam? = navigationProvider.getParam()

        init {
            param?.let { p ->
                _uiState.update {
                    it.copy(userId = p.userId)
                }
            }
        }

        override fun onUsernameChanged(username: String) {
            _uiState.update { it.copy(username = username) }
    	}

        override fun updateErrorMessage(errorMessage: String) {
            _uiState.update { it.copy(errorMessage = errorMessage) }
        }

        override fun navigateToHome() {
            navigationProvider.navigate(Destination.HOME_DESTINATION)
        }
}
