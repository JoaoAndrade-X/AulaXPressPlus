package com.joaoandrade.aulaxpressplus.ui.login

import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.CommandReceiver

interface LoginCommandReceiver :
    CommandReceiver<LoginCommandReceiver> {
    override fun executeCommand(command: Command<LoginCommandReceiver>) {
        command.execute(this)
    }

    fun setUserIdNotFoundError()
    fun setLoginError()
    fun setAuthExceptionError(message: String)
    fun setEmptyFieldError()
    fun setUserNotFoundError()

    fun changePasswordVisibility()
    fun goToUsernameCreation(userId: String)
    fun goToMain()
    fun goToRegister()
    fun onEmailValueChanged(email: String)
    fun onPasswordValueChanged(password: String)
}