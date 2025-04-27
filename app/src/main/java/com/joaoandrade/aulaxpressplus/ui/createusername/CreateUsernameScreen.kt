package com.joaoandrade.aulaxpressplus.ui.createusername

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    val context = LocalContext.current

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage.isNotEmpty()) {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacings.small)
    ) {
        val (appIcon, usernameTextField, continueButton) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier
                .size(components.createUsernameAppIcon)
                .constrainAs(appIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(usernameTextField.top)
                }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(usernameTextField) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = stringResource(R.string.create_username_set),
                style = LocalTextStyle.current.copy(
                    fontSize = fontSizes.textSmall
                ),
            )
            OutlinedTextField(
                value = uiState.username,
                onValueChange = { onExecuteCommand(CreateUsernameCommand.OnUsernameChanged(it)) },
                label = { Text(stringResource(R.string.create_username_user)) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                if (uiState.username.isNotBlank()) {
                    checkUsernameAvailability(uiState.username) { available ->
                        if (available) {
                            updateUsernameInFirestore(uiState.userId, uiState.username) {
                                onExecuteCommand(CreateUsernameCommand.NavigateToHome)
                            }
                        } else {
                            val errorMessage = context.getString(R.string.create_username_taken)
                            onExecuteCommand(
                                CreateUsernameCommand.OnUsernameChanged(errorMessage)
                            )
                        }
                    }
                } else {
                    val errorMessage = context.getString(R.string.create_username_empty)
                    onExecuteCommand(CreateUsernameCommand.OnUsernameChanged(errorMessage))
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(continueButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(stringResource(R.string.create_username_continue))
        }
    }
}

private fun checkUsernameAvailability(username: String, onResult: (Boolean) -> Unit) {
    //val db = FirebaseFirestore.getInstance()
    //db.collection("users")
    //    .whereEqualTo("username", username)
    //    .get()
    //    .addOnSuccessListener { result ->
    //        onResult(result.isEmpty)
    //    }
    //    .addOnFailureListener { exception ->
    //        Log.e("Firestore", "Erro ao verificar username: ${exception.message}")
    //        onResult(false)
    //    }
}

private fun updateUsernameInFirestore(userId: String, username: String, onSuccess: () -> Unit) {
    //val db = FirebaseFirestore.getInstance()
    //val user: MutableMap<String, Any> = hashMapOf("username" to username)  // Ajuste aqui
    //db.collection("users")
    //    .document(userId)
    //    .update(user)
    //    .addOnSuccessListener {
    //        Log.d("Firestore", "Nome de usuário atualizado com sucesso")
    //        onSuccess()
    //    }
    //    .addOnFailureListener { exception ->
    //        Log.e("Firestore", "Erro ao atualizar nome de usuário: ${exception.message}")
    //    }
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
