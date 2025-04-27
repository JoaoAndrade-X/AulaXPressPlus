package com.joaoandrade.aulaxpressplus.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.joaoandrade.aulaxpressplus.shared.bases.BaseViewModel
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.shared.enums.NavigationMode
import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    override val navigationProvider: NavigationProvider,
) : ViewModel(), BaseViewModel<SplashUiState>, SplashCommandReceiver {
    private val _uiState =
        MutableStateFlow(SplashUiState())
    override val uiState = _uiState.asStateFlow()

    override fun onAnimationStart() {
        if (uiState.value.animationState == AnimationState.END) {
            navigateToLogin()
        }
    }

    override fun onAnimationIteration(iteration: Int) {
        if (uiState.value.animationState == AnimationState.MIDDLE) {
            changeAnimationState(AnimationState.END)
        }
        Handler(Looper.getMainLooper()).postDelayed(
            { onAnimationIteration(iteration + 1) },
            200L,)
    }

    override fun onAnimationEnd() {
        when (uiState.value.animationState) {
            AnimationState.START -> changeAnimationState(AnimationState.MIDDLE)
            AnimationState.MIDDLE -> changeAnimationState(AnimationState.END)
            AnimationState.END -> navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        navigationProvider.navigate(
            destination = Destination.LOGIN_DESTINATION,
            mode = NavigationMode.REMOVE_CURRENT_SCREEN,
        )
    }

    private fun changeAnimationState(animationState: AnimationState) {
        _uiState.update {
            it.copy(animationState = animationState)
        }
    }
}
