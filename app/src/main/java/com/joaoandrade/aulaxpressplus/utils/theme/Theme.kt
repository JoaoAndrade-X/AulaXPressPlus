package com.joaoandrade.aulaxpressplus.utils.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.joaoandrade.aulaxpressplus.designs.DarkAppColors
import com.joaoandrade.aulaxpressplus.designs.LightAppColors
import com.joaoandrade.aulaxpressplus.designs.LocalAppColors
import com.joaoandrade.aulaxpressplus.designs.LocalComponentsTint
import com.joaoandrade.aulaxpressplus.designs.LocalTheme
import com.joaoandrade.aulaxpressplus.designs.darkColorScheme
import com.joaoandrade.aulaxpressplus.designs.darkComponentsTint
import com.joaoandrade.aulaxpressplus.designs.lightColorScheme
import com.joaoandrade.aulaxpressplus.designs.lightComponentsTint
import com.joaoandrade.aulaxpressplus.designs.typography
import com.joaoandrade.aulaxpressplus.shared.enums.Theme
import com.joaoandrade.aulaxpressplus.utils.extensions.isDarkMode

@Composable
fun AppTheme(
    theme: Theme = Theme.AUTO,
    content: @Composable () -> Unit,
) {
    val colorScheme = getColorScheme(theme)
    val appColorScheme = getAppColorScheme(theme)
    val componentsTint = getComponentsTint(theme)
    CompositionLocalProvider(
        LocalTheme provides theme,
        LocalAppColors provides appColorScheme,
        LocalComponentsTint provides componentsTint,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = {
                Surface(content = content)
            },
        )
    }
}

@Composable
private fun getColorScheme(theme: Theme) = if (theme.isDarkMode) darkColorScheme else lightColorScheme

@Composable
private fun getAppColorScheme(theme: Theme) = if (theme.isDarkMode) DarkAppColors else LightAppColors

@Composable
fun getComponentsTint(theme: Theme) = if (theme.isDarkMode) darkComponentsTint else lightComponentsTint