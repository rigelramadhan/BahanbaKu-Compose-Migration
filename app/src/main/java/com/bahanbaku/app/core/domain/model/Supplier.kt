package com.bahanbaku.app.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Supplier(
    val product: List<Product>,
    val address: String,
    val origin: Origin,
    val name: String,
    val id: String,
    val addressObj: AddressInput
) : Parcelable
