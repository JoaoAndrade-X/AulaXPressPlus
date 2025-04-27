package com.joaoandrade.aulaxpressplus.designs

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val white = Color(0xFFFFFFFF)
val platinum = Color(0xFFE4E6E8)
val antiFlashWhite = Color(0xFFF2F3F5)
val gainsboro = Color(0xFFDCDCDC)
val silverSand = Color(0xFFBCC2C8)
val phillipineGray = Color(0xFF8C9192)
val granite = Color(0xFF7A8082)
val spanishGray = Color(0xFF999999)
val darkLiver = Color(0xFF4E4E4E)
val outerSpace = Color(0xFF484848)
val blackOlive = Color(0xFF3C3C3C)
val raisinBlack = Color(0xFF242424)
val darkCharcoal = Color(0xFF303030)
val alphaBlack = Color(0x60000000)
val chineseBlack = Color(0xFF121212)
val cordovan = Color(0xFF8E3F49)
val rossoCorsa = Color(0xFFD61E00)
val rosyBrown = Color(0xFFC48F8F)
val onyx = Color(0xFF343A3F)
val independence = Color(0xFF495867)
val tuftsBlue = Color(0xFF4A90E2)
val amber = Color(0xFFFFBD00)
val lightMossGreen = Color(0xFFB9E9AB)
val kellyGreen = Color(0xFF4CBB16)
val caramel = Color(0xFFF8DF97)

internal val lightColorScheme =
    lightColorScheme(
        primary = onyx,
        onPrimary = white,
        background = white,
        onBackground = onyx,
        surface = white,
    )

internal val darkColorScheme =
    darkColorScheme(
        primary = darkCharcoal,
        onPrimary = gainsboro,
        background = chineseBlack,
        onBackground = gainsboro,
        surface = chineseBlack,
    )