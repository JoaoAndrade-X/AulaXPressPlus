package com.joaoandrade.aulaxpressplus.ui.createusername

import com.joaoandrade.aulaxpressplus.shared.bases.BaseUiState

data class CreateUsernameUiState(
    val username: String = "",
    val userId: String = "",
    val errorMessage: String = "",
    override val isLoading: Boolean = false,
) : BaseUiState
