package com.joaoandrade.aulaxpressplus.ui.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
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
import com.joaoandrade.aulaxpressplus.designs.tuftsBlue
import com.joaoandrade.aulaxpressplus.dimensions.components
import com.joaoandrade.aulaxpressplus.dimensions.fontSizes
import com.joaoandrade.aulaxpressplus.dimensions.spacings
import com.joaoandrade.aulaxpressplus.navigation.buildScreenDestination
import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.Screen
import com.joaoandrade.aulaxpressplus.shared.enums.Destination

val loginDestination =
    buildScreenDestination(
        destinationTarget =  Destination.LOGIN_DESTINATION,
        viewModelProvider = { hiltViewModel<LoginViewModel>() },
        screen = LoginScreen,
    )

internal object LoginScreen: Screen<LoginUiState, LoginCommandReceiver> {
    @Composable
    override fun invoke(
        uiState: LoginUiState,
        onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
    ) {
        Content(uiState, onExecuteCommand)
    }

}

@Composable
fun Content(
    uiState: LoginUiState,
    onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit,
) {
    val context = LocalContext.current

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        onExecuteCommand(LoginCommand.HandleGoogleSignInResult(result.data))
    }
    LaunchedEffect(Unit) {
        onExecuteCommand(LoginCommand.InitGoogleSignInLauncher(googleSignInLauncher))
    }

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.hasErrorMessage()) {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacings.small)
    ) {
        val (appIcon, footer, loginArea) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier
                .size(components.loginAppIcon)
                .constrainAs(appIcon) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(loginArea.top)
                }
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(loginArea) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { onExecuteCommand(LoginCommand.OnEmailValueChanged(it)) },
                label = {
                    Text(
                        stringResource(R.string.login_email_label),
                        fontSize = fontSizes.textNormal
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = fontSizes.textNormal)
            )
            Spacer(modifier = Modifier.height(components.loginTextSpacer))
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { onExecuteCommand(LoginCommand.OnPasswordValueChanged(it)) },
                label = {
                    Text(
                        stringResource(R.string.login_password_label),
                        fontSize = fontSizes.textNormal
                    )
                },
                visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { onExecuteCommand(LoginCommand.ChangePasswordVisibility) }) {
                        Icon(
                            imageVector = if (uiState.passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = stringResource(R.string.password_visibility_toggle)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = fontSizes.textNormal)
            )
            Spacer(modifier = Modifier.height(components.loginHeightSpacer))
            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.login_button))
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
                style = LocalTextStyle.current.copy(
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
                    modifier = Modifier.size(components.loginIconButton),
                    onClick = {

                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.windows),
                        contentDescription = stringResource(R.string.microsoft_login_description)
                    )
                }
                IconButton(
                    modifier = Modifier.size(components.loginIconButton),
                    onClick = { onExecuteCommand(LoginCommand.SignInWithGoogle(context)) }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gmail),
                        contentDescription = stringResource(R.string.google_login_description)
                    )
                }
                IconButton(
                    modifier = Modifier.size(components.loginIconButton),
                    onClick = {

                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.git),
                        contentDescription = stringResource(R.string.github_login_description)
                    )
                }
            }
            ClickableText(
                text = AnnotatedString(stringResource(R.string.register_new_account_text)),
                onClick = {
                    onExecuteCommand(LoginCommand.GoToRegister)
                },
                modifier = Modifier
                    .padding(top = spacings.small),
                style = LocalTextStyle.current.copy(
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
    LoginScreen(
        LoginUiState(),
    ) {}
}
