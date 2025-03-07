package com.joaoandrade.aulaxpressplus.ui.createusername

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.joaoandrade.aulaxpressplus.navigation.buildScreenDestination
import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.Screen
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.utils.theme.AppTheme

val createUsernameDestination =
    buildScreenDestination<CreateUsernameViewModel, CreateUsernameCommandReceiver, CreateUsernameUiState>(
        Destination.CREATE_USERNAME_DESTINATION,
        viewModelProvider = { hiltViewModel<CreateUsernameViewModel>() },
        CreateUsernameScreen,
    )

internal object CreateUsernameScreen :
    Screen<CreateUsernameUiState, CreateUsernameCommandReceiver> {
    @Composable
    override operator fun invoke(
        uiState: CreateUsernameUiState,
        onExecuteCommand: (Command<CreateUsernameCommandReceiver>) -> Unit,
    ) {
        Content(uiState, onExecuteCommand)
    }
}

@Composable
private fun Content(
    uiState: CreateUsernameUiState,
    onExecuteCommand: (Command<CreateUsernameCommandReceiver>) -> Unit,
) {
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CreateUsernameScreen(
            CreateUsernameUiState(),
        ) {}
    }
}
