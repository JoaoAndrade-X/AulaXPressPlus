package com.joaoandrade.aulaxpressplus.ui.login

import androidx.lifecycle.ViewModel
import com.joaoandrade.aulaxpressplus.R
import com.joaoandrade.aulaxpressplus.shared.bases.BaseViewModel
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.shared.enums.NavigationMode
import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import com.joaoandrade.aulaxpressplus.shared.providers.StringResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel
    @Inject
    constructor(
        private val stringResourceProvider: StringResourceProvider,
        override val navigationProvider: NavigationProvider,
    ) : ViewModel(), BaseViewModel<LoginUiState>, LoginCommandReceiver {
        private val _uiState = MutableStateFlow(LoginUiState())
        override val uiState = _uiState.asStateFlow()

    override fun goToMain() {
//        navigationProvider.navigate(
//            Destination.HOME_DESTINATION,
//            NavigationMode.CLEAR_SCREEN_STACK,
//        )
    }

    override fun goToRegister() {
        // navigationProvider.navigate(Destination.REGISTER_DESTINATION)
    }

    override fun setUserIdNotFoundError() {
        _uiState.update { it.copy(
            errorMessage = stringResourceProvider.getString(R.string.user_id_not_found)
        ) }
    }

    override fun setLoginError() {
        _uiState.update { it.copy(
            errorMessage = stringResourceProvider.getString(R.string.login_error_google_auth)
        ) }
    }

    override fun setAuthExceptionError(message: String) {
        _uiState.update { it.copy(
            errorMessage = stringResourceProvider.getString(R.string.error_google_auth_exception, message)
        ) }
    }

    override fun setEmptyFieldError() {
        _uiState.update { it.copy(
            errorMessage = stringResourceProvider.getString(R.string.error_empty_fields)
        ) }
    }

    override fun setUserNotFoundError() {
        _uiState.update { it.copy(
            errorMessage = stringResourceProvider.getString(R.string.user_not_found)
        ) }
    }

    override fun changePasswordVisibility() {
        val visible = uiState.value.passwordVisible
        _uiState.update { it.copy(passwordVisible = !visible) }
    }

    override fun goToUsernameCreation(userId: String) {
        /*navigationProvider.navigate(
            CreateUsernameParams(userId)
        )*/
    }

    override fun onEmailValueChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    override fun onPasswordValueChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }
}