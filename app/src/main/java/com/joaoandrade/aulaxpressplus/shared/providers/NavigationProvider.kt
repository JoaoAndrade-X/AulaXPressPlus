package com.joaoandrade.aulaxpressplus.shared.providers

import android.app.Activity
import com.joaoandrade.aulaxpressplus.shared.enums.NavigationControllerType

class NavigationProvider(
    private val navControllerProvider: NavControllerProvider,
    private val navigationControllerType: NavigationControllerType,
) {
    @PublishedApi
    internal val navController get() = navControllerProvider.getNavController(navigationControllerType)

    fun navigateBack() {
        if (navController?.previousBackStackEntry != null) {
            navController?.popBackStack()
        } else {
            (navController?.context as? Activity)?.moveTaskToBack(true)
        }
    }
}