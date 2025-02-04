package com.joaoandrade.aulaxpressplus.shared.providers

import androidx.navigation.NavHostController
import com.joaoandrade.aulaxpressplus.shared.enums.NavigationControllerType
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavControllerProvider
    @Inject
    constructor() {
        private var navControllerMap: MutableMap<NavigationControllerType, NavHostController> = mutableMapOf()

        fun getCurrentRoute(key: NavigationControllerType) = getNavController(key)?.currentDestination?.route

        fun screenDestinationFlow(key: NavigationControllerType) =
            getNavController(key)?.currentBackStackEntryFlow?.map{ it.destination.route }

        fun getNavController(key: NavigationControllerType) = navControllerMap[key]

        fun setNavController(
            key: NavigationControllerType,
            navController: NavHostController,
        ) {
            navControllerMap[key] = navController
        }
    }
