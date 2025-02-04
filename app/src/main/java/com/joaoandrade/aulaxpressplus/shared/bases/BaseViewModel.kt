package com.joaoandrade.aulaxpressplus.shared.bases

import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import kotlinx.coroutines.flow.StateFlow

interface BaseViewModel<State>: StatelessViewModel {
    val uiState: StateFlow<State>
}

interface StatelessViewModel {
    val navigationProvider: NavigationProvider

    fun navigateBack() {
        navigationProvider.navigateBack()
    }
}