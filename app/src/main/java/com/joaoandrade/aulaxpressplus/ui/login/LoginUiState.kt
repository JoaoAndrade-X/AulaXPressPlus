package com.joaoandrade.aulaxpressplus.ui.login

import com.joaoandrade.aulaxpressplus.shared.bases.BaseUiState

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errorMessage: String = "",
    val passwordVisible: Boolean = false,
    override val isLoading: Boolean = false,
) : BaseUiState {
    fun hasEmptyField() = email.isEmpty() || password.isEmpty()
    fun hasErrorMessage() = errorMessage.isNotEmpty()
}