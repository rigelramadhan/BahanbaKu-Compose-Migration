package com.bahanbaku.app.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Checkout(
    val ingredientsName: String,
    val ingredientsQuantity: Int,
    val ingredientsPrice: Int,
    val image: String
) : Parcelable
