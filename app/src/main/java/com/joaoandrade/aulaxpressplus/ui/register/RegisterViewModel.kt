package com.joaoandrade.aulaxpressplus.ui.register

import androidx.lifecycle.ViewModel
import com.joaoandrade.aulaxpressplus.shared.bases.BaseViewModel
import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject
constructor(
    override val navigationProvider: NavigationProvider,
) : ViewModel(), BaseViewModel<RegisterUiState>, RegisterCommandReceiver {
    private val _uiState =
        MutableStateFlow(RegisterUiState())
    override val uiState = _uiState.asStateFlow()
    override fun onUsernameChanged(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    override fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    override fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    override fun onConfirmPasswordChanged(confirmPassword: String) {
        _uiState.update { it.copy(confirmPassword = confirmPassword) }
    }

    override fun navigateBack() {
        navigationProvider.navigateBack()
    }

    override fun updatePasswordVisibility() {
        val visible = uiState.value.passwordVisible
        _uiState.update { it.copy(passwordVisible = !visible) }
    }

    override fun updateConfirmPasswordVisibility() {
        val visible = uiState.value.confirmPasswordVisible
        _uiState.update { it.copy(confirmPasswordVisible = !visible) }
    }
}