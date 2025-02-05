package com.joaoandrade.aulaxpressplus.shared.providers

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.get
import com.joaoandrade.aulaxpressplus.shared.bases.BaseParam
import com.joaoandrade.aulaxpressplus.shared.bases.ResultParam
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.shared.enums.NavigationControllerType
import com.joaoandrade.aulaxpressplus.shared.enums.NavigationMode

class NavigationProvider(
    private val navControllerProvider: NavControllerProvider,
    private val navigationControllerType: NavigationControllerType,
) {
    @PublishedApi
    internal val navController get() = navControllerProvider.getNavController(navigationControllerType)

    fun navigate(
        destination: Destination,
        mode: NavigationMode? =null,
    ) {
        navController?.navigate(
            route = destination.route,
            navOptions = getNavOptions(mode)
        )
    }

    fun <Param: BaseParam> navigate(
        param: Param,
        mode: NavigationMode? = null,
    ) {
        navController?.graph?.get(param.destination.route)?.let {
            navigateWithParam(
                navDestination = it,
                param = param,
                navOptions = getNavOptions(mode),
            )
        }
    }

    fun navigateBack() {
        if (navController?.previousBackStackEntry != null) {
            navController?.popBackStack()
        } else {
            (navController?.context as? Activity)?.moveTaskToBack(true)
        }
    }

    fun <Result : ResultParam> navigateBack(result: Result) {
        navController?.previousBackStackEntry?.savedStateHandle?.set(
            result::class.simpleName.orEmpty(),
            result
        )
        navController?.popBackStack()
    }

    private fun <Param> navigateWithParam(
        navDestination: NavDestination,
        param: Param,
        navOptions: NavOptions? = null,
    ) {
        val route = navDestination.route
        if (navDestination.id != 0 && route != null) {
            navController?.navigate(navDestination.id, bundleOf(route to param), navOptions)
        }
    }

    private fun getNavOptions(mode: NavigationMode?): NavOptions? {
        val currentDestination = navController?.currentDestination
        return if (mode != null && currentDestination != null) {
            NavOptions.Builder().apply {
                val destinationId =
                    when (mode) {
                        NavigationMode.REMOVE_CURRENT_SCREEN -> currentDestination.id
                        NavigationMode.CLEAR_SCREEN_STACK -> 0
                    }
                setPopUpTo(destinationId, true)
            }.build()
        } else {
            null
        }
    }
}