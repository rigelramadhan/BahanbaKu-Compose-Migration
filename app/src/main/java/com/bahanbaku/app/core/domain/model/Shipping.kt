package com.bahanbaku.app.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shipping(
    val cost: Int,
    val distance: Int,
    val id: String
) : Parcelable
