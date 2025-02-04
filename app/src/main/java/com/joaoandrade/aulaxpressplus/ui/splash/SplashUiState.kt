package com.joaoandrade.aulaxpressplus.ui.splash

import androidx.annotation.RawRes
import com.joaoandrade.aulaxpressplus.R
import com.joaoandrade.aulaxpressplus.shared.bases.BaseUiState

data class SplashUiState(
    val animationState: AnimationState = AnimationState.START,
): BaseUiState

enum class AnimationState(
    @RawRes val animationResId: Int,
    val iterations: Int,
) {
    START(R.raw.lootie_loading, 1)
}