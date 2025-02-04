package com.joaoandrade.aulaxpressplus.shared.enums

enum class Destination {
    SPLASH_SCREEN,
    ;

    val route get() = this.name.lowercase()
}