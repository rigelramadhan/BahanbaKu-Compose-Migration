package com.bahanbaku.app.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val createdAt: String,
//    val deletedAt: String,
    val author: String,
    val imageUrl: String,
    val portion: Int,
    val rating: Double,
    val description: String,
    val time: Int,
    val title: String,
    val recipeId: String,
    val updatedAt: String
) : Parcelable
