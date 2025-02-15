package com.joaoandrade.aulaxpressplus.ui.main

import com.joaoandrade.aulaxpressplus.navigation.ScreenDestination
import com.joaoandrade.aulaxpressplus.shared.enums.Theme
import com.joaoandrade.aulaxpressplus.ui.splash.splashDestination

internal data class MainUiState(
    val firstScreenDestination: ScreenDestination = splashDestination,
    val allScreenDestinations: List<ScreenDestination>? = null,
    val theme: Theme = Theme.AUTO,
) {
    val firstScreenDestinationRoute get() = firstScreenDestination.destination.route
}