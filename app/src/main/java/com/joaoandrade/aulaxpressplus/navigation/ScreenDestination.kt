package com.joaoandrade.aulaxpressplus.navigation

import androidx.compose.runtime.Composable
import com.joaoandrade.aulaxpressplus.shared.enums.Destination

interface ScreenDestination {
    val destination: Destination
    val screen: @Composable () -> Unit
}