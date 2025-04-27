package com.joaoandrade.aulaxpressplus.shared.bases

import android.os.Parcelable
import com.joaoandrade.aulaxpressplus.shared.enums.Destination

interface BaseParam: Parcelable {
    val destination: Destination
}

interface ResultParam: Parcelable