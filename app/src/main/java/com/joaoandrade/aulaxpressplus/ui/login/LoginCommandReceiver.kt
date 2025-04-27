package com.joaoandrade.aulaxpressplus.ui.login

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
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
    fun initGoogleSignInLauncher(launcher: ActivityResultLauncher<Intent>)
    fun signInWithGoogle(context: Context)
    fun handleGoogleSignInResult(data: Intent?)
}