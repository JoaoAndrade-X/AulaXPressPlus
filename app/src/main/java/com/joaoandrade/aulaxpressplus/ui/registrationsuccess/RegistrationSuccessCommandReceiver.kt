package com.joaoandrade.aulaxpressplus.ui.registrationsuccess

import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.CommandReceiver

internal interface RegistrationSuccessCommandReceiver :
    CommandReceiver<RegistrationSuccessCommandReceiver> {
    override fun executeCommand(command: Command<RegistrationSuccessCommandReceiver>) {
        command.execute(this)
    }

    fun navigateToLogin()
}
