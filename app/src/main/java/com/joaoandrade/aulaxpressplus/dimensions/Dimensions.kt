package com.joaoandrade.aulaxpressplus.dimensions

import androidx.compose.runtime.Composable
import com.joaoandrade.aulaxpressplus.dimensions.Density.Companion.getDensity
import com.joaoandrade.aulaxpressplus.dimensions.densities.defaults.Component
import com.joaoandrade.aulaxpressplus.dimensions.densities.defaults.FontSize
import com.joaoandrade.aulaxpressplus.dimensions.densities.defaults.Spacing
import com.joaoandrade.aulaxpressplus.dimensions.densities.sw360.ComponentSw360
import com.joaoandrade.aulaxpressplus.dimensions.densities.sw600.ComponentSw600
import com.joaoandrade.aulaxpressplus.dimensions.densities.sw600.FontSizeSw600
import com.joaoandrade.aulaxpressplus.dimensions.densities.sw600.SpacingSw600

val spacings: Spacing
    @Composable get() =
        getDensity(
            default = Spacing(),
            sw600 = SpacingSw600(),
        )

val fontSizes: FontSize
    @Composable get() =
        getDensity(
            default = FontSize(),
            sw600 = FontSizeSw600(),
        )

val components: Component
    @Composable get() =
        getDensity(
            default = Component(),
            sw360 = ComponentSw360(),
            sw600 = ComponentSw600(),
        )