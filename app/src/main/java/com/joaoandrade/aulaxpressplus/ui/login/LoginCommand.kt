package com.joaoandrade.aulaxpressplus.ui.login

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
}