package com.joaoandrade.aulaxpressplus.ui.register

import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.CommandReceiver

interface RegisterCommandReceiver :
    CommandReceiver<RegisterCommandReceiver> {
    override fun executeCommand(command: Command<RegisterCommandReceiver>) {
        command.execute(this)
    }

    fun onUsernameChanged(username: String)

    fun onEmailChanged(email: String)

    fun onPasswordChanged(password: String)

    fun onConfirmPasswordChanged(confirmPassword: String)

    fun navigateBack()

    fun updatePasswordVisibility()

    fun updateConfirmPasswordVisibility()
}