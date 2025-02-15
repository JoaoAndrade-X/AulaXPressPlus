package com.joaoandrade.aulaxpressplus.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.utils.extensions.isDarkMode
import com.joaoandrade.aulaxpressplus.utils.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            setSystemBarsTheme(!uiState.theme.isDarkMode)
            AppTheme(uiState.theme) {
                Navigation(
                    uiState = uiState,
                    onExecuteCommand = viewModel::executeCommand
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.executeCommand(MainCommand.OnStart)
    }

    private fun setSystemBarsTheme(isLightMode: Boolean) =
        with(WindowInsetsControllerCompat(window, window.decorView)) {
            isAppearanceLightNavigationBars = isLightMode
            isAppearanceLightStatusBars = isLightMode
        }

    @Composable
    private fun Navigation(
        uiState: MainUiState,
        onExecuteCommand: (Command<MainCommandReceiver>) -> Unit,
    ) {
        if (uiState.allScreenDestinations == null) return
        val navController = rememberNavController()
        onExecuteCommand(MainCommand.SetNavHostController(navController))
        NavHost(
            navController = navController,
            startDestination = uiState.firstScreenDestinationRoute,
            modifier = Modifier.fillMaxSize(),
        ) {
            uiState.allScreenDestinations.forEach { screenDestination ->
                composable(
                    route = screenDestination.destination.route,
                    enterTransition = { if (fromLogin()) fadeIn(tween()) else enterTransition() },
                    exitTransition = { if (fromLogin()) fadeOut(tween()) else exitTransition() },
                    popEnterTransition = { popEnterTransition() },
                    popExitTransition = { popExitTransition() }
                ) {
                    screenDestination.screen()
                }
            }
        }
    }

}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.fromLogin() =
    this.initialState.destination.route == Destination.SPLASH_DESTINATION.route

private fun popExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(),
    ) +
            shrinkOut(
                shrinkTowards = Alignment.CenterEnd,
                animationSpec = tween(),
            )
}

private fun popEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(),
    ) +
            expandIn(
                expandFrom = Alignment.CenterStart,
                animationSpec = tween(),
            )
}

private fun enterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(),
    ) +
            expandIn(
                expandFrom = Alignment.CenterEnd,
                animationSpec = tween(),
            )
}

private fun exitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(),
    ) +
            shrinkOut(
                shrinkTowards = Alignment.CenterStart,
                animationSpec = tween(),
            )
}
