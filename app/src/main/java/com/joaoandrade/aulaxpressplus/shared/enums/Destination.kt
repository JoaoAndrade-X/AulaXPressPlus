package com.joaoandrade.aulaxpressplus.shared.enums

enum class Destination {
    SPLASH_DESTINATION,
    LOGIN_DESTINATION,
    HOME_DESTINATION,
    REGISTER_DESTINATION,
    CREATE_USERNAME_DESTINATION,
    REGISTRATION_SUCCESS_DESTINATION
    ;

    val route get() = this.name.lowercase()
}