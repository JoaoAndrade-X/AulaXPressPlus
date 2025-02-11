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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.joaoandrade.aulaxpressplus.R
import com.joaoandrade.aulaxpressplus.navigation.buildScreenDestination
import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.Screen
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.utils.components.userHasUsername

val loginDestination =
    buildScreenDestination<LoginViewModel, LoginCommandReceiver, LoginUiState>(
        Destination.LOGIN_DESTINATION,
        LoginScreen,
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
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let { idToken ->
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { authResult ->
                        if (authResult.isSuccessful) {
                            val userId = auth.currentUser?.uid
                            if (userId != null) {
                                userHasUsername(context, userId) { hasUsername ->
                                    if (hasUsername) {
                                        onExecuteCommand(LoginCommand.GoToMain)
                                    } else {
                                        onExecuteCommand(LoginCommand.GoToUsernameCreation(userId))
                                    }
                                }
                            } else {
                                onExecuteCommand(LoginCommand.SetUserIdNotFoundError)
                                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_LONG).show()
                            }
                        } else {
                            onExecuteCommand(LoginCommand.SetLoginError)
                            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_LONG).show()
                        }
                    }
            }
        } catch (e: ApiException) {
            onExecuteCommand(LoginCommand.SetAuthExceptionError(e.message.orEmpty()))
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.hasErrorMessage()) {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (appIcon, footer, loginArea) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
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
                label = { Text(stringResource(R.string.login_email_label)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { onExecuteCommand(LoginCommand.OnPasswordValueChanged(it)) },
                label = { Text(stringResource(R.string.login_password_label)) },
                visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { onExecuteCommand(LoginCommand.ChangePasswordVisibility) }) {
                        Icon(
                            imageVector = if (uiState.passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = stringResource(R.string.password_visibility_toggle)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (uiState.hasEmptyField()) {
                        onExecuteCommand(LoginCommand.SetEmptyFieldError)
                    } else {
                        auth.signInWithEmailAndPassword(uiState.email, uiState.password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    onExecuteCommand(LoginCommand.GoToMain)
                                } else {
                                    onExecuteCommand(LoginCommand.SetUserNotFoundError)
                                }
                            }
                    }
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
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    modifier = Modifier.size(50.dp),
                    onClick = {

                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.windows),
                        contentDescription = stringResource(R.string.microsoft_login_description)
                    )
                }
                IconButton(
                    modifier = Modifier.size(50.dp),
                    onClick = {
                        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(context.getString(R.string.default_web_client_id))
                            .requestEmail()
                            .build()

                        val googleSignInClient = GoogleSignIn.getClient(context, gso)
                        googleSignInLauncher.launch(googleSignInClient.signInIntent)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gmail),
                        contentDescription = stringResource(R.string.google_login_description)
                    )
                }
                IconButton(
                    modifier = Modifier.size(50.dp),
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
                    .padding(top = 16.dp),
                style = LocalTextStyle.current.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp
                )
            )
        }
    }
}
