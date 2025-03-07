package com.joaoandrade.aulaxpressplus.ui.createusername

import com.joaoandrade.aulaxpressplus.shared.bases.Command

internal sealed interface CreateUsernameCommand :
    Command<CreateUsernameCommandReceiver> {
    data object CreateUsername : CreateUsernameCommand {
        override fun execute(receiver: CreateUsernameCommandReceiver) {
            receiver.createUsername()
        }
    }
}
