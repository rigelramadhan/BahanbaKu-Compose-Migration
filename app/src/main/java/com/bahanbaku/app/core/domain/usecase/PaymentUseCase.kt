package com.bahanbaku.app.core.domain.usecase

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.domain.model.ProductsData
import io.reactivex.Flowable
import java.io.File

interface PaymentUseCase {
    fun createDirectPayment(
        token: String,
        products: ProductsData,
        id: String
    ): Flowable<Resource<PostCreateDirectPaymentResponse>>
    fun getDirectPaymentInfo(token: String): Flowable<Resource<GetDirectPaymentInfoResponse>>
    fun submitPaymentProof(
        token: String,
        file: File,
        id: String
    ): Flowable<Resource<PostSubmitProofResponse>>

    fun getDirectOrderHistory(token: String): Flowable<Resource<List<OrderHistoryItem>>>
    fun getDirectOrderDetail(
        token: String,
        id: String
    ): Flowable<Resource<DirectPaymentDetailResult>>
}