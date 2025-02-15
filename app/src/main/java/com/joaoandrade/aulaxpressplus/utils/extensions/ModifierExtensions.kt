package com.joaoandrade.aulaxpressplus.utils.extensions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import com.joaoandrade.aulaxpressplus.designs.LocalAppColors
import com.joaoandrade.aulaxpressplus.dimensions.components

@Composable
fun Modifier.setButtonBorders(
    enabled: Boolean,
    buttonShape: Shape,
): Modifier =
    if (enabled.not()) {
        border(
            border =
            BorderStroke(
                width = components.buttonBorderStroke,
                color = LocalAppColors.current.primaryDisabledBorder,
            ),
            shape = buttonShape,
        )
    } else {
        this
    }

fun Modifier.debounceClickable(
    enabled: Boolean = true,
    debounceInterval: Long = 200,
    onClick: () -> Unit,
): Modifier =
    composed {
        then(
            debounceClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current,
                enabled = enabled,
                debounceInterval = debounceInterval,
                onClick = onClick,
            ),
        )
    }

fun Modifier.debounceClickable(
    interactionSource: MutableInteractionSource,
    indication: Indication?,
    enabled: Boolean = true,
    debounceInterval: Long = 200,
    onClick: () -> Unit,
): Modifier =
    composed {
        var lastClickTime by remember { mutableLongStateOf(0L) }
        then(
            clickable(interactionSource, indication, enabled) {
                val currentTime = System.currentTimeMillis()
                if ((currentTime - lastClickTime) < debounceInterval) return@clickable
                lastClickTime = currentTime
                onClick()
            },
        )
    }

fun Modifier.visibility(isVisible: Boolean): Modifier =
    composed {
        then(
            alpha(if (isVisible) 1f else 0f),
        )
    }