package com.bahanbaku.app.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressInput(
    val zipCode: Int,
    val province: String,
    val city: String,
    val street: String,
    val latitude: Double,
    val district: String,
    val label: String,
    val longitude: Double,
    val receiverName: String,
    val receiverPhoneNumber: String
) : Parcelable
