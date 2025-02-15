package com.joaoandrade.aulaxpressplus.designs

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.joaoandrade.aulaxpressplus.R
import com.joaoandrade.aulaxpressplus.dimensions.fontSizes

private val barlowFontFamily =
    FontFamily(
        Font(R.font.barlow_bold, FontWeight.Bold),
        Font(R.font.barlow_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.barlow_light_italic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.barlow_medium, FontWeight.Medium),
        Font(R.font.barlow_medium_italic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.barlow_light_italic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.barlow_regular, FontWeight.Normal),
        Font(R.font.barlow_semi_bold, FontWeight.SemiBold),
        Font(R.font.muli_light, FontWeight.ExtraLight),
    )

internal val typography
    @Composable get() =
        Typography(
            bodyLarge =
            TextStyle(
                fontFamily = barlowFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = fontSizes.textLarge,
            ),
            bodyMedium =
            TextStyle(
                fontFamily = barlowFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = fontSizes.textMedium,
            ),
            bodySmall =
            TextStyle(
                fontFamily = barlowFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = fontSizes.textSmall,
            ),
            titleLarge =
            TextStyle(
                fontFamily = barlowFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = fontSizes.textLarge,
            ),
            titleMedium =
            TextStyle(
                fontFamily = barlowFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = fontSizes.textMedium,
            ),
            titleSmall =
            TextStyle(
                fontFamily = barlowFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = fontSizes.textSmall,
            ),
            labelMedium =
            TextStyle(
                fontFamily = barlowFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = fontSizes.textMedium,
            ),
            labelSmall =
            TextStyle(
                fontFamily = barlowFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = fontSizes.textMediumSmall,
            ),
        )