package com.bahanbaku.app.core.data.repository

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.datasource.RemoteDataSource
import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.domain.model.ProductsData
import com.bahanbaku.app.core.domain.repository.IPaymentRepository
import kotlinx.coroutines.flow.Flow
import java.io.File

class PaymentRepository(private val remoteDataSource: RemoteDataSource) : IPaymentRepository {
    override fun getDirectPaymentInfo(token: String): Flow<Resource<GetDirectPaymentInfoResponse>> =
        remoteDataSource.getDirectPaymentInfo(token)

    override fun createDirectPayment(
        token: String,
        products: ProductsData,
        id: String
    ): Flow<Resource<PostCreateDirectPaymentResponse>> =
        remoteDataSource.createDirectPayment(token, products, id)

    override fun submitPaymentProof(
        token: String,
        file: File,
        id: String
    ): Flow<Resource<PostSubmitProofResponse>> =
        remoteDataSource.submitPaymentProof(token, file, id)

    override fun getDirectOrderHistory(
        token: String
    ): Flow<Resource<List<OrderHistoryItem>>> = remoteDataSource.getDirectOrderHistory(token)

    override fun getDirectOrderDetail(
        token: String,
        id: String
    ): Flow<Resource<DirectPaymentDetailResult>> =
        remoteDataSource.getDirectOrderDetail(token, id)
}