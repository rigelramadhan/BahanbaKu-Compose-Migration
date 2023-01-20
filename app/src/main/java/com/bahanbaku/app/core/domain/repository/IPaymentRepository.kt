package com.bahanbaku.app.core.domain.repository

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.domain.model.ProductsData
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IPaymentRepository {
    fun createDirectPayment(
        token: String,
        products: ProductsData,
        id: String
    ): Flow<Resource<PostCreateDirectPaymentResponse>>

    fun submitPaymentProof(token: String, file: File, id: String): Flow<Resource<PostSubmitProofResponse>>
    fun getDirectPaymentInfo(token: String): Flow<Resource<GetDirectPaymentInfoResponse>>
    fun getDirectOrderHistory(token: String): Flow<Resource<List<OrderHistoryItem>>>
    fun getDirectOrderDetail(
        token: String,
        id: String
    ): Flow<Resource<DirectPaymentDetailResult>>
}