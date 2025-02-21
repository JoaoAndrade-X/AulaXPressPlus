package com.joaoandrade.aulaxpressplus.ui.login

import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.CommandReceiver

interface LoginCommandReceiver :
    CommandReceiver<LoginCommandReceiver> {
    override fun executeCommand(command: Command<LoginCommandReceiver>) {
        command.execute(this)
    }

    fun changePasswordVisibility()
    fun goToRegister()
    fun onEmailValueChanged(email: String)
    fun onPasswordValueChanged(password: String)
}