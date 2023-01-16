package com.bahanbaku.app.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val firstName: String,
    val lastName: String,
    val createdAt: String,
    val isVerified: Boolean,
    val profileImage: String,
    val userId: String,
    val email: String,
    val updatedAt: String,
    val phoneNumber: String,
) : Parcelable
