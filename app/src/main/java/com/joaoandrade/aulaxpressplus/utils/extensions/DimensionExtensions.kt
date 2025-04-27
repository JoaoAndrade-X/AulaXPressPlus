package com.joaoandrade.aulaxpressplus.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
private fun <T> getDensity(onConvert: Density.() -> T) = with(LocalDensity.current) { onConvert() }

val Int.toDp: Dp
    @Composable get() =
        getDensity {
            this@toDp.toDp()
        }


val Float.toDp: Dp
    @Composable get() =
        getDensity {
            this@toDp.toDp()
        }

val Int.toSp: TextUnit
    @Composable get() =
        getDensity {
            this@toSp.toSp()
        }