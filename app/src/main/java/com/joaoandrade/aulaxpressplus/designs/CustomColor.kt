package com.joaoandrade.aulaxpressplus.designs

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val topContainer: Color = Color.Unspecified,
    val topContainerPressed: Color = Color.Unspecified,
    val topContainerSelectedContent: Color = Color.Unspecified,
    val topContainerUnselectedContent: Color = Color.Unspecified,
    val card: Color = Color.Unspecified,
    val divider: Color = Color.Unspecified,
    val primaryPressed: Color = Color.Unspecified,
    val primaryDisabled: Color = Color.Unspecified,
    val mosaicPressed: Color = Color.Unspecified,
    val mosaicBorder: Color = Color.Unspecified,
    val primaryDisabledBorder: Color = Color.Unspecified,
    val onBackgroundLight: Color = Color.Unspecified,
    val mainScreenBackgroundGradient: Brush = Brush.linearGradient(),
) {
    val primary @Composable get() = MaterialTheme.colorScheme.primary
    val onPrimary @Composable get() = MaterialTheme.colorScheme.onPrimary
    val background @Composable get() = MaterialTheme.colorScheme.background
    val onBackground @Composable get() = MaterialTheme.colorScheme.onBackground
    val surface @Composable get() = MaterialTheme.colorScheme.surface
}

val LocalAppColors =
    staticCompositionLocalOf {
        AppColors()
    }

val LightAppColors =
    AppColors(
        topContainer = antiFlashWhite,
        topContainerPressed = platinum,
        divider = platinum,
        card = white,
        primaryPressed = chineseBlack,
        primaryDisabled = silverSand,
        topContainerSelectedContent = independence,
        mosaicPressed = rossoCorsa,
        topContainerUnselectedContent = granite,
        mosaicBorder = gainsboro,
        onBackgroundLight = independence,
        mainScreenBackgroundGradient = lightGradient(),
    )

val DarkAppColors =
    AppColors(
        topContainer = darkCharcoal,
        topContainerPressed = raisinBlack,
        divider = outerSpace,
        card = raisinBlack,
        primaryPressed = outerSpace,
        primaryDisabled = chineseBlack,
        topContainerSelectedContent = gainsboro,
        mosaicPressed = darkLiver,
        topContainerUnselectedContent = spanishGray,
        primaryDisabledBorder = outerSpace,
        onBackgroundLight = spanishGray,
        mainScreenBackgroundGradient = darkGradient(),
    )

private fun lightGradient() =
    Brush.linearGradient(
        colorStops =
        arrayOf(
            0f to white,
            0.60f to white,
            1f to phillipineGray,
        ),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(0f, 0f),
    )

private fun darkGradient(): Brush {
    return Brush.linearGradient(
        colorStops =
        arrayOf(
            0.0f to chineseBlack,
            0.8f to chineseBlack,
            1f to blackOlive,
        ),
        start = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
        end = Offset(0.25f, 0f),
    )
}