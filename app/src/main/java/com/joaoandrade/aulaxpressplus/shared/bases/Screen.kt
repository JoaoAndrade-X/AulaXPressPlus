package com.joaoandrade.aulaxpressplus.shared.bases

import androidx.compose.runtime.Composable

interface Screen<UiState, CommandReceiver> {
    @Composable
    operator fun invoke(
        uiState: UiState,
        onExecuteCommand: (Command<CommandReceiver>) -> Unit,
    )
}

interface StatelessScreen<CommandReceiver> {
    @Composable
    operator fun invoke(onExecuteCommand: (Command<CommandReceiver>) -> Unit)
}