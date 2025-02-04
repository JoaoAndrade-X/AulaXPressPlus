package com.joaoandrade.aulaxpressplus.ui.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.joaoandrade.aulaxpressplus.navigation.buildScreenDestination
import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.Screen
import com.joaoandrade.aulaxpressplus.shared.enums.Destination

val splashDestination = buildScreenDestination<SplashViewModel, SplashCommandReceiver, SplashUiState>(
    Destination.SPLASH_SCREEN,
    SplashScreen,
)

object SplashScreen : Screen<SplashUiState, SplashCommandReceiver> {
    @Composable
    override fun invoke(
        uiState: SplashUiState,
        onExecuteCommand: (Command<SplashCommandReceiver>) -> Unit
    ) {
        Text("aaaaaaaaaaaaaaaaaaaaaaaaaaa")
    }
}