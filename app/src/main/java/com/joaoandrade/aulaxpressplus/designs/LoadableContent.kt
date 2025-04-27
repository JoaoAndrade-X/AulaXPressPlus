package com.joaoandrade.aulaxpressplus.designs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.joaoandrade.aulaxpressplus.R
import com.joaoandrade.aulaxpressplus.utils.components.KeyboardUtils.HideKeyboard
import com.joaoandrade.aulaxpressplus.utils.theme.AppTheme

@Composable
fun LoadableContent (
    isLoading: Boolean,
    content: @Composable () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()
        LoadingScreen(isLoading)
    }
}


@Composable
private fun LoadingScreen(isLoading: Boolean) {
    if (isLoading) {
        HideKeyboard()

        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false,
            )
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize(),
            ) {
                val animationComposition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        R.raw.lootie_loading,
                    ),
                )

                LottieAnimation(
                    modifier =
                        Modifier
                            .align(Alignment.Center),
                    composition = animationComposition,
                    iterations = LottieConstants.IterateForever
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoadableContentPreview() {
    AppTheme {
        LoadableContent(true) {
        }
    }
}