package com.joaoandrade.aulaxpressplus.ui.main

import com.joaoandrade.aulaxpressplus.navigation.ScreenDestination
import com.joaoandrade.aulaxpressplus.ui.splash.splashDestination

internal data class MainUiState(
    val firstScreenDestination: ScreenDestination = splashDestination,
    val allScreenDestinations: List<ScreenDestination>? = null,
) {
    val firstScreenDestinationRoute get() = firstScreenDestination.destination.route
}