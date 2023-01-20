package com.bahanbaku.app.core.data.repository

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.datasource.RemoteDataSource
import com.bahanbaku.app.core.data.remote.response.PaymentItem
import com.bahanbaku.app.core.domain.repository.IUtilRepository
import kotlinx.coroutines.flow.Flow

class UtilRepository(
    private val remoteDataSource: RemoteDataSource
) : IUtilRepository {

    override fun getPaymentMethods(token: String): Flow<Resource<List<PaymentItem>>> =
        remoteDataSource.getPaymentMethods(token)
}