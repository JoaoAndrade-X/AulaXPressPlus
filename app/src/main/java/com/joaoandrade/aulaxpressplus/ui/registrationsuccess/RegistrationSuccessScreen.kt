package com.joaoandrade.aulaxpressplus.ui.registrationsuccess

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.joaoandrade.aulaxpressplus.navigation.buildScreenDestination
import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.Screen
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.utils.theme.AppTheme

val registrationSuccessDestination =
    buildScreenDestination<RegistrationSuccessViewModel, RegistrationSuccessCommandReceiver, RegistrationSuccessUiState>(
        Destination.REGISTRATION_SUCCESS_DESTINATION,
	    viewModelProvider = { hiltViewModel<RegistrationSuccessViewModel>() },
        RegistrationSuccessScreen,
    )

internal object RegistrationSuccessScreen : Screen<RegistrationSuccessUiState, RegistrationSuccessCommandReceiver> {
    @Composable
    override operator fun invoke(
        uiState: RegistrationSuccessUiState,
        onExecuteCommand: (Command<RegistrationSuccessCommandReceiver>) -> Unit,
    ) {
	Content(uiState, onExecuteCommand)
    }
}

@Composable
private fun Content(
    uiState: RegistrationSuccessUiState,
    onExecuteCommand: (Command<RegistrationSuccessCommandReceiver>) -> Unit,
) {
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        RegistrationSuccessScreen(
            RegistrationSuccessUiState(),
        ) {}
    }
}
