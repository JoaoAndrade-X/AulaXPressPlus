package com.joaoandrade.aulaxpressplus.shared.enums

enum class Theme {
    DARK,
    LIGHT,
    AUTO,
    ;

    companion object {
        fun getTheme(theme: String?) =
            theme?.let {
                if (it == DARK.name) DARK else LIGHT
            } ?: AUTO
    }
}