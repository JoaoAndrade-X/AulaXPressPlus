package com.joaoandrade.aulaxpressplus.ui.createusername

import com.joaoandrade.aulaxpressplus.shared.bases.BaseUiState

data class CreateUsernameUiState(
    val userId: String = "",
    val username: String = "",
    val errorMessage: String = "",
    override val isLoading: Boolean = false,
) : BaseUiState
