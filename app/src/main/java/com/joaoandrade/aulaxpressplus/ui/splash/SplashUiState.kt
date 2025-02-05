package com.joaoandrade.aulaxpressplus.ui.splash

import androidx.annotation.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.joaoandrade.aulaxpressplus.R
import com.joaoandrade.aulaxpressplus.shared.bases.BaseUiState

data class SplashUiState(
    val animationState: AnimationState = AnimationState.START,
): BaseUiState

enum class AnimationState(
    @RawRes val animationResId: Int,
    val iterations: Int,
) {
    START(R.raw.lottie_splash_start, 1),
    MIDDLE(R.raw.lottie_splash_middle, LottieConstants.IterateForever),
    END(R.raw.lottie_splash_end, 1)
}