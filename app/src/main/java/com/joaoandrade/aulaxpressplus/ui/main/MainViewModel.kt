package com.joaoandrade.aulaxpressplus.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.joaoandrade.aulaxpressplus.navigation.ScreenDestination
import com.joaoandrade.aulaxpressplus.shared.enums.NavigationControllerType
import com.joaoandrade.aulaxpressplus.shared.providers.NavControllerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel
    @Inject
    constructor(
        private val allScreenDestinations: Array<ScreenDestination>,
        private val navControllerProvider: NavControllerProvider,
    ) : ViewModel(), MainCommandReceiver {
        private val _uiState = MutableStateFlow(MainUiState())
        val uiState = _uiState.asStateFlow()

        init {
            initUiState()
        }

        private fun initUiState() =
            viewModelScope.launch {
                _uiState.update { it.copy(allScreenDestinations = allScreenDestinations.toList()) }
            }

        override fun setNavHostController(navHostController: NavHostController) {
            navControllerProvider.setNavController(
                NavigationControllerType.MAIN_NAVIGATION,
                navHostController
            )
        }
}