package com.joaoandrade.aulaxpressplus.designs

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ComponentsTint(
    val mosaicIcon: Color = Color.Unspecified
)

val LocalComponentsTint =
    staticCompositionLocalOf {
        ComponentsTint()
    }

val lightComponentsTint = ComponentsTint()

val darkComponentsTint = ComponentsTint(white)
