package com.joaoandrade.aulaxpressplus.navigation.params

import kotlinx.parcelize.Parcelize
import com.joaoandrade.aulaxpressplus.shared.bases.BaseParam
import com.joaoandrade.aulaxpressplus.shared.enums.Destination

@Parcelize
data class CreateUsernameParam(
    val userId: String = "",
) : BaseParam {
    override val destination: Destination
        get() = Destination.CREATE_USERNAME_DESTINATION
}