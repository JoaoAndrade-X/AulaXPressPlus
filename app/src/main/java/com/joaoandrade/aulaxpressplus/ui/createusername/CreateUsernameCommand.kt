package com.joaoandrade.aulaxpressplus.ui.createusername

import com.joaoandrade.aulaxpressplus.shared.bases.Command

internal sealed interface CreateUsernameCommand :
    Command<CreateUsernameCommandReceiver> {
    data class OnUsernameChanged(private val username: String) : CreateUsernameCommand {
        override fun execute(receiver: CreateUsernameCommandReceiver) {
            receiver.onUsernameChanged(username)
        }
    }

    data class UpdateErrorMessage(private val errorMessage: String) : CreateUsernameCommand {
        override fun execute(receiver: CreateUsernameCommandReceiver) {
            receiver.updateErrorMessage(errorMessage)
        }
    }

    data object NavigateToHome : CreateUsernameCommand {
        override fun execute(receiver: CreateUsernameCommandReceiver) {
            receiver.navigateToHome()
        }
    }
}
