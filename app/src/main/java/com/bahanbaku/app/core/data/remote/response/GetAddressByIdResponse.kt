package com.bahanbaku.app.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetAddressByIdResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: AddressResultItem
)
