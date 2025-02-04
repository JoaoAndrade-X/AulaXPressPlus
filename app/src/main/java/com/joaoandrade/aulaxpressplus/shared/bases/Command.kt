package com.joaoandrade.aulaxpressplus.shared.bases

interface Command<CommandReceiver> {
    fun execute(receiver: CommandReceiver)
}