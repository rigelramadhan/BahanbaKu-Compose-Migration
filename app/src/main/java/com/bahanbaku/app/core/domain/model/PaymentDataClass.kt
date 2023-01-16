package com.bahanbaku.app.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentDataClass(
    val date: String,
    val paymentMethod: String,
) : Parcelable
