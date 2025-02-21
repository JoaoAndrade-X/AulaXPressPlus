package com.joaoandrade.aulaxpressplus.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joaoandrade.aulaxpressplus.designs.LoadableContent
import com.joaoandrade.aulaxpressplus.shared.bases.BaseUiState
import com.joaoandrade.aulaxpressplus.shared.bases.BaseViewModel
import com.joaoandrade.aulaxpressplus.shared.bases.CommandReceiver
import com.joaoandrade.aulaxpressplus.shared.bases.Screen
import com.joaoandrade.aulaxpressplus.shared.bases.StatelessScreen
import com.joaoandrade.aulaxpressplus.shared.bases.StatelessViewModel
import com.joaoandrade.aulaxpressplus.shared.enums.Destination

fun <VM, CR, UiState> buildScreenDestination(
    destinationTarget: Destination,
    viewModelProvider: @Composable () -> VM,
    screen: Screen<UiState, CR>,
    hasBackHandler: Boolean = true,
) where CR : CommandReceiver<CR>, VM : BaseViewModel<UiState>, VM : ViewModel, VM : CommandReceiver<CR>, UiState : BaseUiState =
    object : ScreenDestination {
        override val destination: Destination = destinationTarget
        override val screen = @Composable {
            val viewModel = viewModelProvider()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            if (hasBackHandler) {
                BackHandler {
                    viewModel.navigateBack()
                }
            }

            LoadableContent(isLoading = uiState.isLoading) {
                screen(uiState, viewModel::executeCommand)
            }
        }
    }

fun <VM, CR> buildStatelessScreenDestination(
    destinationTarget: Destination,
    viewModelProvider: @Composable () -> VM,
    screen: StatelessScreen<CR>,
    hasBackHandler: Boolean = true,
) where CR : CommandReceiver<CR>, VM : ViewModel, VM : StatelessViewModel, VM : CommandReceiver<CR> =
    object : ScreenDestination {
        override val destination: Destination = destinationTarget
        override val screen = @Composable {
            val viewModel = viewModelProvider()

            if (hasBackHandler) {
                BackHandler {
                    viewModel.navigateBack()
                }
            }

            screen(viewModel::executeCommand)
        }
    }