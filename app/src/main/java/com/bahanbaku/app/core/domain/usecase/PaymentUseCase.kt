package com.bahanbaku.app.core.domain.usecase

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.domain.model.ProductsData
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PaymentUseCase {
    fun createDirectPayment(
        token: String,
        products: ProductsData,
        id: String
    ): Flow<Resource<PostCreateDirectPaymentResponse>>
    fun getDirectPaymentInfo(token: String): Flow<Resource<GetDirectPaymentInfoResponse>>
    fun submitPaymentProof(
        token: String,
        file: File,
        id: String
    ): Flow<Resource<PostSubmitProofResponse>>

    fun getDirectOrderHistory(token: String): Flow<Resource<List<OrderHistoryItem>>>
    fun getDirectOrderDetail(
        token: String,
        id: String
    ): Flow<Resource<DirectPaymentDetailResult>>
}