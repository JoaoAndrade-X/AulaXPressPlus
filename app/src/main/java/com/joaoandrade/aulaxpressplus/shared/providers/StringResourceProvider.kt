package com.joaoandrade.aulaxpressplus.shared.providers

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringResourceProvider
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) {
        fun getString(
            @StringRes stringId: Int,
            vararg args: String,
        ) = if (args.isNotEmpty()) {
            context.getString(stringId, *args)
        } else {
            context.getString(stringId)
        }
    }