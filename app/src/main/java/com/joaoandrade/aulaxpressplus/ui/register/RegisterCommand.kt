package com.joaoandrade.aulaxpressplus.ui.register

import com.joaoandrade.aulaxpressplus.shared.bases.Command

internal sealed interface RegisterCommand :
    Command<RegisterCommandReceiver> {
    data class OnUsernameChanged(val username: String): RegisterCommand {
        override fun execute(receiver: RegisterCommandReceiver) {
            receiver.onUsernameChanged(username)
        }
    }

    data class OnEmailChanged(val email: String): RegisterCommand {
        override fun execute(receiver: RegisterCommandReceiver) {
            receiver.onEmailChanged(email)
        }
    }

    data class OnPasswordChanged(val password: String): RegisterCommand {
        override fun execute(receiver: RegisterCommandReceiver) {
            receiver.onPasswordChanged(password)
        }
    }

    data class OnConfirmPasswordChanged(val confirmPassword: String): RegisterCommand {
        override fun execute(receiver: RegisterCommandReceiver) {
            receiver.onConfirmPasswordChanged(confirmPassword)
        }
    }

    data object NavigateBack : RegisterCommand {
        override fun execute(receiver: RegisterCommandReceiver) {
            receiver.navigateBack()
        }
    }

    data object UpdatePasswordVisibility : RegisterCommand {
        override fun execute(receiver: RegisterCommandReceiver) {
            receiver.updatePasswordVisibility()
        }
    }

    data object UpdateConfirmPasswordVisibility : RegisterCommand {
        override fun execute(receiver: RegisterCommandReceiver) {
            receiver.updateConfirmPasswordVisibility()
        }
    }
}