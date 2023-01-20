package com.bahanbaku.app.core.domain.usecase

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.DirectPaymentDetailResult
import com.bahanbaku.app.core.data.remote.response.GetDirectPaymentInfoResponse
import com.bahanbaku.app.core.data.remote.response.OrderHistoryItem
import com.bahanbaku.app.core.data.remote.response.PostSubmitProofResponse
import com.bahanbaku.app.core.domain.model.ProductsData
import com.bahanbaku.app.core.domain.repository.IPaymentRepository
import kotlinx.coroutines.flow.Flow
import java.io.File

class PaymentInteractor(private val paymentRepository: IPaymentRepository) : PaymentUseCase {
    override fun createDirectPayment(token: String, products: ProductsData, id: String) =
        paymentRepository.createDirectPayment(token, products, id)

    override fun submitPaymentProof(
        token: String,
        file: File,
        id: String
    ): Flow<Resource<PostSubmitProofResponse>> =
        paymentRepository.submitPaymentProof(token, file, id)

    override fun getDirectPaymentInfo(token: String): Flow<Resource<GetDirectPaymentInfoResponse>> =
        paymentRepository.getDirectPaymentInfo(token)

    override fun getDirectOrderHistory(
        token: String
    ): Flow<Resource<List<OrderHistoryItem>>> = paymentRepository.getDirectOrderHistory(token)

    override fun getDirectOrderDetail(
        token: String,
        id: String
    ): Flow<Resource<DirectPaymentDetailResult>> = paymentRepository.getDirectOrderDetail(token, id)
}