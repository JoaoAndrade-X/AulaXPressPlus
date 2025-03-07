package com.joaoandrade.aulaxpressplus.ui.createusername

import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.CommandReceiver

internal interface CreateUsernameCommandReceiver :
    CommandReceiver<CreateUsernameCommandReceiver> {
    override fun executeCommand(command: Command<CreateUsernameCommandReceiver>) {
        command.execute(this)
    }

    fun createUsername()
}
