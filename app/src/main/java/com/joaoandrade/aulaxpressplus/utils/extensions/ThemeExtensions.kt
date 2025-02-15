package com.joaoandrade.aulaxpressplus.utils.extensions

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.joaoandrade.aulaxpressplus.shared.enums.Theme

val Theme.isDarkMode
    @Composable get() =
        when (this) {
            Theme.AUTO -> isSystemInDarkTheme()
            else -> this == Theme.DARK
        }