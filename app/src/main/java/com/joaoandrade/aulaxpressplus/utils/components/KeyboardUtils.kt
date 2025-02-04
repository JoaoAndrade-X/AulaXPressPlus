package com.joaoandrade.aulaxpressplus.utils.components

import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

object KeyboardUtils {
    @Composable
    fun isKeyboardVisible(): Boolean {
        var isKeyboardOpen by remember { mutableStateOf(false) }
        val view = LocalView.current
        val viewTreeObserver = view.viewTreeObserver
        DisposableEffect(viewTreeObserver) {
            val listener =
                ViewTreeObserver.OnGlobalLayoutListener {
                    isKeyboardOpen = ViewCompat
                        .getRootWindowInsets(view)
                        ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
                }
            viewTreeObserver.addOnGlobalLayoutListener(listener)
            onDispose {
                viewTreeObserver.removeOnGlobalLayoutListener(listener)
            }
        }
        return isKeyboardOpen
    }

    @Composable
    fun HideKeyboard() = LocalSoftwareKeyboardController.current?.hide()
}