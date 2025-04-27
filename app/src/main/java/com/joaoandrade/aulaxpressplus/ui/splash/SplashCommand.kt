package com.joaoandrade.aulaxpressplus.ui.splash

import com.joaoandrade.aulaxpressplus.shared.bases.Command

internal sealed interface SplashCommand :
    Command<SplashCommandReceiver> {
    data object OnAnimationStart : SplashCommand {
        override fun execute(receiver: SplashCommandReceiver) {
            receiver.onAnimationStart()
        }
    }

    data object OnAnimationEnd : SplashCommand {
        override fun execute(receiver: SplashCommandReceiver) {
            receiver.onAnimationEnd()
        }
    }

    class OnAnimationIteration(
        private val iteration: Int,
    ) : SplashCommand {
        override fun execute(receiver: SplashCommandReceiver) {
            receiver.onAnimationIteration(iteration)
        }
    }
}