package com.joaoandrade.aulaxpressplus.ui.registrationsuccess

import com.joaoandrade.aulaxpressplus.shared.bases.Command

internal sealed interface RegistrationSuccessCommand :
    Command<RegistrationSuccessCommandReceiver> {
    data object NavigateToLogin : RegistrationSuccessCommand {
        override fun execute(receiver: RegistrationSuccessCommandReceiver) {
            receiver.navigateToLogin()
        }
    }
}
