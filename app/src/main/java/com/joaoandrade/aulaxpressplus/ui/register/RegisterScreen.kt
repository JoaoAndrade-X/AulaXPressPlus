package com.joaoandrade.aulaxpressplus.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.joaoandrade.aulaxpressplus.R
import com.joaoandrade.aulaxpressplus.designs.LocalAppColors
import com.joaoandrade.aulaxpressplus.designs.rossoCorsa
import com.joaoandrade.aulaxpressplus.designs.tuftsBlue
import com.joaoandrade.aulaxpressplus.dimensions.components
import com.joaoandrade.aulaxpressplus.dimensions.fontSizes
import com.joaoandrade.aulaxpressplus.dimensions.spacings
import com.joaoandrade.aulaxpressplus.navigation.buildScreenDestination
import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.Screen
import com.joaoandrade.aulaxpressplus.shared.enums.Destination

val registerDestination =
    buildScreenDestination(
        destinationTarget =  Destination.REGISTER_DESTINATION,
        viewModelProvider = { hiltViewModel<RegisterViewModel>() },
        screen = RegisterScreen,
    )

internal object RegisterScreen: Screen<RegisterUiState, RegisterCommandReceiver> {
    @Composable
    override fun invoke(
        uiState: RegisterUiState,
        onExecuteCommand: (Command<RegisterCommandReceiver>) -> Unit
    ) {
        Content(uiState, onExecuteCommand)
    }

}

@Composable
fun Content(
    uiState: RegisterUiState,
    onExecuteCommand: (Command<RegisterCommandReceiver>) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacings.small)
    ) {
        val (backText, appIcon, registerArea, footer) = createRefs()

        Text(
            text = stringResource(R.string.back),
            color = LocalAppColors.current.primary,
            fontSize = fontSizes.textMedium,
            modifier = Modifier
                .clickable { onExecuteCommand(RegisterCommand.NavigateBack) }
                .padding(spacings.xxSmall)
                .constrainAs(backText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
        )
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier
                .size(components.registerAppIcon)
                .constrainAs(appIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(registerArea.top)
                }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(registerArea) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            OutlinedTextField(
                value = uiState.username,
                onValueChange = { onExecuteCommand(RegisterCommand.OnUsernameChanged(it)) },
                label = {
                    Text(
                        stringResource(R.string.register_username_label),
                        fontSize = fontSizes.textNormal
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = fontSizes.textNormal)
            )
            Spacer(modifier = Modifier.height(components.registerTextSpacer))
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { onExecuteCommand(RegisterCommand.OnEmailChanged(it)) },
                label = {
                    Text(
                        stringResource(R.string.register_email_label),
                        fontSize = fontSizes.textNormal
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = fontSizes.textNormal)
            )
            Spacer(modifier = Modifier.height(components.registerTextSpacer))
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { onExecuteCommand(RegisterCommand.OnPasswordChanged(it)) },
                label = {
                    Text(
                        stringResource(R.string.register_password_label),
                        fontSize = fontSizes.textNormal
                    )
                },
                visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { onExecuteCommand(RegisterCommand.UpdatePasswordVisibility) }) {
                        Icon(
                            imageVector = if (uiState.passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = stringResource(R.string.password_visibility_toggle)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = fontSizes.textNormal)
            )
            Spacer(modifier = Modifier.height(components.registerTextSpacer))
            OutlinedTextField(
                value = uiState.confirmPassword,
                onValueChange = { onExecuteCommand(RegisterCommand.OnConfirmPasswordChanged(it)) },
                label = {
                    Text(
                        stringResource(R.string.register_confirm_password_label),
                        fontSize = fontSizes.textNormal
                    )
                },
                visualTransformation = if (uiState.confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { onExecuteCommand(RegisterCommand.UpdateConfirmPasswordVisibility) }) {
                        Icon(
                            imageVector = if (uiState.confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = stringResource(R.string.password_visibility_toggle)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = fontSizes.textNormal)
            )
            Spacer(modifier = Modifier.height(components.registerHeightSpacer))
            Button(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.register_button))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(footer) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.other_options_text),
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(
                    fontSize = fontSizes.textMediumSmall
                ),
                modifier = Modifier.padding(bottom = spacings.xxSmall)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    modifier = Modifier.size(components.registerIconButton),
                    onClick = {
                        // Facebook Sign-In logic
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.windows),
                        contentDescription = stringResource(R.string.microsoft_register_description)
                    )
                }
                IconButton(
                    modifier = Modifier.size(components.registerIconButton),
                    onClick = {

                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gmail),
                        contentDescription = stringResource(R.string.google_register_description)
                    )
                }
                IconButton(
                    modifier = Modifier.size(components.registerIconButton),
                    onClick = {
                        // GitHub Sign-In logic
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.git),
                        contentDescription = stringResource(R.string.github_register_description)
                    )
                }
            }
            ClickableText(
                text = AnnotatedString(stringResource(R.string.register_text)),
                onClick = {
                    onExecuteCommand(RegisterCommand.NavigateBack)
                },
                modifier = Modifier
                    .padding(top = spacings.small),
                style = TextStyle(
                    color = tuftsBlue,
                    fontSize = fontSizes.textSmall
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
fun Preview() {
    RegisterScreen(
        RegisterUiState(),
    ) {}
}