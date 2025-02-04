package com.joaoandrade.aulaxpressplus.ui.main

import androidx.navigation.NavHostController
import com.joaoandrade.aulaxpressplus.shared.bases.Command

internal sealed interface MainCommand: Command<MainCommandReceiver> {
    data class SetNavHostController(private val navHostController: NavHostController): MainCommand {
        override fun execute(receiver: MainCommandReceiver) {
            receiver.setNavHostController(navHostController)
        }
    }
}