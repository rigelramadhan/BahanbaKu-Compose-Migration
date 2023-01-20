package com.bahanbaku.app.core.domain.repository

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.PaymentItem
import kotlinx.coroutines.flow.Flow

interface IUtilRepository {
    fun getPaymentMethods(token: String): Flow<Resource<List<PaymentItem>>>
}