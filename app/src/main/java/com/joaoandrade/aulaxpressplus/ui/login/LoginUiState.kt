package com.joaoandrade.aulaxpressplus.ui.login

import com.joaoandrade.aulaxpressplus.shared.bases.BaseUiState

data class LoginUiState(
    val email: String = "null",
    val password: String = "null",
    val errorMessage: String = "null",
    val passwordVisible: Boolean = false,
    override val isLoading: Boolean = false,
) : BaseUiState {
    fun hasEmptyField() = email.isEmpty() || password.isEmpty()
    fun hasErrorMessage() = errorMessage.isNotEmpty()
}