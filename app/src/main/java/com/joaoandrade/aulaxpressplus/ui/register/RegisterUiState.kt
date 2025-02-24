package com.joaoandrade.aulaxpressplus.ui.register

import com.joaoandrade.aulaxpressplus.shared.bases.BaseUiState

data class RegisterUiState(
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var passwordVisible: Boolean = false,
    var confirmPasswordVisible: Boolean = false,
): BaseUiState