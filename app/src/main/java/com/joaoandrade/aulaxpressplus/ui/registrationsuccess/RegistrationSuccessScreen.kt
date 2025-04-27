package com.joaoandrade.aulaxpressplus.ui.registrationsuccess

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.joaoandrade.aulaxpressplus.R
import com.joaoandrade.aulaxpressplus.dimensions.components
import com.joaoandrade.aulaxpressplus.dimensions.fontSizes
import com.joaoandrade.aulaxpressplus.dimensions.spacings
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
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacings.small)
    ) {
        val (successArea, backButton) = createRefs()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(successArea) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(components.registrationSuccessAppIcon)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.registration_success_message),
                color = MaterialTheme.colorScheme.onBackground,
                style = LocalTextStyle.current.copy(
                    fontSize = fontSizes.textMediumSmall,
                    textAlign = TextAlign.Center
                ),
            )
        }

        Button(
            onClick = { onExecuteCommand(RegistrationSuccessCommand.NavigateToLogin) },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(backButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text(stringResource(R.string.registration_success_button))
        }
    }
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
