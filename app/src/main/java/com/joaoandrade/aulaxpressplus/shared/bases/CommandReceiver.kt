package com.joaoandrade.aulaxpressplus.shared.bases

interface CommandReceiver<CommandReceiver> {
    fun executeCommand(command: Command<CommandReceiver>)
}