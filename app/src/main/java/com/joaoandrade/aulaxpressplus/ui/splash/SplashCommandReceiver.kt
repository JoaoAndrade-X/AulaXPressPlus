package com.joaoandrade.aulaxpressplus.ui.splash

import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.CommandReceiver

interface SplashCommandReceiver :
    CommandReceiver<SplashCommandReceiver>{
    override fun executeCommand(command: Command<SplashCommandReceiver>) {
        command.execute(this)
    }
}