package com.joaoandrade.aulaxpressplus.ui.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.joaoandrade.aulaxpressplus.navigation.buildScreenDestination
import com.joaoandrade.aulaxpressplus.shared.bases.Command
import com.joaoandrade.aulaxpressplus.shared.bases.Screen
import com.joaoandrade.aulaxpressplus.shared.enums.Destination

val splashDestination =
    buildScreenDestination<SplashViewModel, SplashCommandReceiver, SplashUiState>(
        destinationTarget =  Destination.SPLASH_DESTINATION,
        viewModelProvider = { hiltViewModel<SplashViewModel>() },
        screen = SplashScreen,
    )

object SplashScreen : Screen<SplashUiState, SplashCommandReceiver> {
    @Composable
    override fun invoke(
        uiState: SplashUiState,
        onExecuteCommand: (Command<SplashCommandReceiver>) -> Unit,
    ) {
        LoadingAnimation(
            uiState = uiState,
            onExecuteCommand = onExecuteCommand,
        )
    }
}

@Composable
private fun LoadingAnimation(
    uiState: SplashUiState,
    onExecuteCommand: (Command<SplashCommandReceiver>) -> Unit,
    dynamicLottieProperties: LottieDynamicProperties? = null,
) {
    val compositionResult =
        rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(uiState.animationState.animationResId),
        )
    val animatable = rememberLottieAnimatable()

    LaunchedEffect(animatable.progress == 0f) {
        if (animatable.progress == 0f) {
            onExecuteCommand(SplashCommand.OnAnimationStart)
        }
    }

    LaunchedEffect(animatable.iteration, animatable.iterations) {
        onExecuteCommand(SplashCommand.OnAnimationIteration(animatable.iteration))
    }

    LaunchedEffect(
        uiState.animationState,
    ) {
        val composition = compositionResult.await()
        animatable.animate(
            composition,
            iterations = uiState.animationState.iterations,
            cancellationBehavior = LottieCancellationBehavior.OnIterationFinish,
            speed = 1.3f,
        )
        if (animatable.isAtEnd) {
            onExecuteCommand(SplashCommand.OnAnimationEnd)
        }
    }

    Column(
        modifier =
        Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
    ) {
        LottieAnimation(
            modifier = Modifier.scale(0.4f),
            composition = animatable.composition,
            progress = { animatable.progress },
            contentScale = ContentScale.Crop,
            dynamicProperties = dynamicLottieProperties,
        )
    }
}

@PreviewLightDark
@Composable
fun Preview() {
    SplashScreen(
        SplashUiState(animationState = AnimationState.END),
    ) {}
}
