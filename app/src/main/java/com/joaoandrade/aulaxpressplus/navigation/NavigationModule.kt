package com.joaoandrade.aulaxpressplus.navigation

import com.joaoandrade.aulaxpressplus.ui.login.loginDestination
import com.joaoandrade.aulaxpressplus.ui.splash.splashDestination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NavigationModule {
    @Provides
    @Singleton
    fun allDestinations(): Array<ScreenDestination> =
        arrayOf(
            splashDestination,
            loginDestination,
        )
}