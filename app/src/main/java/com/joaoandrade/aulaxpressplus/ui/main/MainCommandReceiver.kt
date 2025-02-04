package com.joaoandrade.aulaxpressplus.ui.main

import androidx.navigation.NavHostController
import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.CommandReceiver

internal interface MainCommandReceiver: CommandReceiver<MainCommandReceiver> {
    override fun executeCommand(command: Command<MainCommandReceiver>) {
        command.execute(this)
    }

    fun setNavHostController(navHostController: NavHostController)
}