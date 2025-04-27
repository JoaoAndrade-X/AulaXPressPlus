package com.joaoandrade.aulaxpressplus.ui.login

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.joaoandrade.aulaxpressplus.shared.bases.Command

internal sealed interface LoginCommand :
    Command<LoginCommandReceiver> {
    data object ChangePasswordVisibility : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.changePasswordVisibility()
        }
    }

    data object GoToRegister : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.goToRegister()
        }
    }

    data class SignInWithGoogle(val context: Context) : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.signInWithGoogle(context)
        }
    }

    data class OnEmailValueChanged(val email: String) : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.onEmailValueChanged(email)
        }
    }

    data class OnPasswordValueChanged(val password: String) : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.onPasswordValueChanged(password)
        }
    }

    data class InitGoogleSignInLauncher(val launcher: ActivityResultLauncher<Intent>) : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.initGoogleSignInLauncher(launcher)
        }
    }

    data class HandleGoogleSignInResult(val data: Intent?) : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.handleGoogleSignInResult(data)
        }
    }
}