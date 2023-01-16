package com.bahanbaku.app.core.domain.model

data class ProductsData(
    val products: List<ProductJSONFormat>,
    val addressId: String
)

data class ProductJSONFormat(
    val id: String,
    val quantity: Int,
)