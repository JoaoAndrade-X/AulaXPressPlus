package com.joaoandrade.aulaxpressplus.utils.extensions

import androidx.compose.ui.graphics.Color

fun Color.opacity(value: Float) =
    copy(
        alpha =
            when {
                value >= 1f -> 1f
                value <= 0f -> 0f
                else -> value
            },
    )