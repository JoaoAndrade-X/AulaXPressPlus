package com.joaoandrade.aulaxpressplus.ui.login

import com.joaoandrade.aulaxpressplus.shared.bases.Command

internal sealed interface LoginCommand :
    Command<LoginCommandReceiver> {
    data class SetAuthExceptionError(val message: String) : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.setAuthExceptionError(message)
        }
    }

    data object SetUserIdNotFoundError : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.setUserIdNotFoundError()
        }
    }
    data object SetLoginError : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.setLoginError()
        }
    }

    data object SetEmptyFieldError : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.setEmptyFieldError()
        }
    }
    data object SetUserNotFoundError : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.setUserNotFoundError()
        }
    }

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

    data object GoToMain : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.goToMain()
        }
    }

    data class GoToUsernameCreation(val userId: String) : LoginCommand {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.goToUsernameCreation(userId)
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