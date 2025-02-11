package com.joaoandrade.aulaxpressplus.navigation

import com.joaoandrade.aulaxpressplus.shared.enums.NavigationControllerType
import com.joaoandrade.aulaxpressplus.shared.providers.NavControllerProvider
import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object NavigationControllerModule {
    @Provides
    fun navigationProviderMain(
        navControllerProvider: NavControllerProvider,
    ) = NavigationProvider(
        navControllerProvider,
        NavigationControllerType.MAIN_NAVIGATION
    )
}